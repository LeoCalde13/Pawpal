package com.caldeira.pawpal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.caldeira.pawpal.DATABASE_BREEDS_TABLE
import com.caldeira.pawpal.EMPTY_STRING

/**
 * Cat details to be displayed in app's UI.
 */
@Entity(tableName = DATABASE_BREEDS_TABLE)
data class CatDetails(
    @PrimaryKey val id: String,
    var name: String,
    val lifeExpectancy: Int,
    var isFavorite: Boolean,
    val origin: String = EMPTY_STRING,
    val temperament: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
) {
    override fun toString(): String {
        return "CatDetails(name='$name', isFavorite=$isFavorite, id='$id')"
    }
}