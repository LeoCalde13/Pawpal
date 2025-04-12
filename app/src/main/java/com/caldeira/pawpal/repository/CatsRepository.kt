package com.caldeira.pawpal.repository

import android.util.Log
import com.caldeira.pawpal.EMPTY_STRING
import com.caldeira.pawpal.data.CatsApi
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.model.CatDto
import com.caldeira.pawpal.model.NetworkResult
import com.caldeira.pawpal.model.handleResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

private const val LIMIT = 20
private const val PAGE = 0
private const val LIFESPAN_DELIMITER = " - "
private const val TEMPERAMENT_DELIMITER = ", "

/**
 * Handles getting cats data using [CatsApi]
 */
class CatsRepository {
    private val catsBreeds = mutableListOf<CatDetails>()

    suspend fun getCatBreeds(): List<CatDetails> {
        if (catsBreeds.isNotEmpty()) return catsBreeds.map { it.copy() }

        val result = handleResult { CatsApi().getBreeds(LIMIT, PAGE) }
        if (result !is NetworkResult.Success) {
            Log.e("CatsRepository", "Error retrieving cats' breeds | result=$result")
            return emptyList()
        }

        return mapWithImages(result.data).also { it.map { k -> catsBreeds.add(k.copy()) } }
    }

    private suspend fun mapWithImages(data: List<CatDto>): List<CatDetails> {
        return withContext(Dispatchers.IO) {
            val deferredResults = data.map { cat ->
                async {
                    cat.referenceImageId ?: return@async mapToCatDetails(cat)

                    val imageResult =
                        handleResult { CatsApi().getBreedsImageUrl(cat.referenceImageId) }
                    if (imageResult !is NetworkResult.Success) {
                        Log.e(
                            "CatsRepository",
                            "Error retrieving image for ${cat.name} | imageResult=$imageResult"
                        )
                        return@async mapToCatDetails(cat)
                    }

                    val image = imageResult.data

                    Log.d("CatsRepository", "got cat image | imageUrl=${image.url}")
                    return@async mapToCatDetails(cat, image.url)
                }
            }

            deferredResults.awaitAll()
        }
    }

    fun setBreedFavoriteState(id: String, isFavorite: Boolean) {
        val cat = catsBreeds.find { it.id == id }
        if (cat != null) cat.isFavorite = isFavorite
    }

    private fun mapToCatDetails(catDto: CatDto, imageUrl: String = EMPTY_STRING) = CatDetails(
        id = catDto.id,
        name = catDto.name,
        lifeExpectancy = catDto.lifeSpan
            .split(LIFESPAN_DELIMITER)
            .firstOrNull()?.toIntOrNull() ?: -1,
        isFavorite = false,
        origin = catDto.origin,
        temperament = catDto.temperament.split(TEMPERAMENT_DELIMITER),
        description = catDto.description,
        imageUrl = imageUrl
    )
}