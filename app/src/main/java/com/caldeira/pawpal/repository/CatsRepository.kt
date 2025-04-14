package com.caldeira.pawpal.repository

import android.util.Log
import com.caldeira.pawpal.EMPTY_STRING
import com.caldeira.pawpal.data.BreedsDao
import com.caldeira.pawpal.data.CatsApi
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.model.CatDto
import com.caldeira.pawpal.model.NetworkResult
import com.caldeira.pawpal.model.handleResult
import com.caldeira.pawpal.model.toCatDetails
import com.caldeira.pawpal.model.toCatDetailsList
import com.caldeira.pawpal.model.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val LIMIT = 40
private const val PAGE = 0
private const val LIFESPAN_DELIMITER = " - "

/**
 * Handles getting cats data using [CatsApi]
 */
class CatsRepository @Inject constructor(private val breedsDb: BreedsDao) {
    private var isCacheInitialized = false

    suspend fun getCatBreeds(): List<CatDetails> = withContext(Dispatchers.IO) {
        if (isCacheInitialized) return@withContext getCachedBreeds()

        val result = handleResult { CatsApi().getBreeds(LIMIT, PAGE) }
        if (result !is NetworkResult.Success) {
            Log.e("CatsRepository", "Error retrieving cats' breeds | result=$result")
            return@withContext getCachedBreeds()
        }

        cacheBreeds(result.data)
        return@withContext getCachedBreeds()
    }

    suspend fun getBreedDetails(id: String) =
        withContext(Dispatchers.IO) { breedsDb.getBreedById(id)?.toCatDetails() }

    suspend fun setBreedFavoriteState(id: String, isFavorite: Boolean) =
        withContext(Dispatchers.IO) {
            breedsDb.updateFavorite(id, isFavorite)
        }

    private suspend fun cacheBreeds(data: List<CatDto>) {
        val cacheJob = CoroutineScope(Dispatchers.IO).launch {
            val deferredResults = data.map { cat ->
                async {
                    cat.referenceImageId ?: return@async

                    // get image
                    val imageUrl = getReferenceImageUrl(cat.referenceImageId)

                    // map to cat details data class
                    if (breedsDb.getBreedById(cat.id) != null) return@async

                    val breed = mapToCatDetails(cat, imageUrl)
                    Log.d("CatsRepository", "caching breed | $breed")

                    // cache the breed into the db
                    breedsDb.insert(breed.toEntity())
                }

            }

            deferredResults.awaitAll()
        }

        cacheJob.join()
    }

    private suspend fun getReferenceImageUrl(referenceImage: String): String {
        val imageResult =
            handleResult { CatsApi().getBreedsImageUrl(referenceImage) }
        if (imageResult !is NetworkResult.Success) {
            Log.e(
                "CatsRepository",
                "Error retrieving image $referenceImage | imageResult=$imageResult"
            )
            return EMPTY_STRING
        }

        return imageResult.data.url
    }

    private suspend fun getCachedBreeds() = withContext(Dispatchers.IO) { breedsDb.getAll().toCatDetailsList() }

    private fun mapToCatDetails(catDto: CatDto, imageUrl: String = EMPTY_STRING) = CatDetails(
        id = catDto.id,
        name = catDto.name,
        lifeExpectancy = catDto.lifeSpan
            .split(LIFESPAN_DELIMITER)
            .firstOrNull()?.toIntOrNull() ?: -1,
        isFavorite = false,
        origin = catDto.origin,
        temperament = catDto.temperament,
        description = catDto.description,
        imageUrl = imageUrl
    )
}