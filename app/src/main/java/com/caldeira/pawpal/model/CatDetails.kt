package com.caldeira.pawpal.model

import com.caldeira.pawpal.EMPTY_STRING

/**
 * Cat details to be displayed in app's UI.
 */
data class CatDetails(
    val id: String,
    var name: String,
    val lifeExpectancy: Int,
    var isFavorite: Boolean,
    val origin: String = EMPTY_STRING,
    val temperament: List<String> = emptyList(),
    val description: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
)