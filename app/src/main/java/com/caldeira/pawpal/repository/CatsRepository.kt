package com.caldeira.pawpal.repository

import android.util.Log
import com.caldeira.pawpal.data.CatsApi
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.model.CatDto
import com.caldeira.pawpal.model.NetworkResult
import com.caldeira.pawpal.model.handleResult

private const val LIMIT = 20
private const val PAGE = 0
private const val LIFESPAN_DELIMITER = " - "
private const val TEMPERAMENT_DELIMITER = ", "

/**
 * Handles getting cats data using [CatsApi]
 */
class CatsRepository {
    suspend fun getCatBreeds(): List<CatDetails> {
        val result = handleResult { CatsApi().getBreeds(LIMIT, PAGE) }
        if (result !is NetworkResult.Success) {
            Log.e("CatsRepository", "Error retrieving cats' breeds | result=$result")
            return emptyList()
        }

        return mapToCatDetails(result.data)
    }


    private fun mapToCatDetails(breedsResponse: List<CatDto>) = breedsResponse.map { dto ->
        CatDetails(
            name = dto.name,
            lifeExpectancy = dto.lifeSpan.split(LIFESPAN_DELIMITER).firstOrNull()?.toIntOrNull() ?: -1,
            isFavorite = false,
            origin = dto.origin,
            temperament = dto.temperament.split(TEMPERAMENT_DELIMITER),
            description = dto.description,
        )
    }
}