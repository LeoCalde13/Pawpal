package com.caldeira.pawpal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.caldeira.pawpal.DATABASE_BREEDS_TABLE
import com.caldeira.pawpal.EMPTY_STRING

@Entity(tableName = DATABASE_BREEDS_TABLE)
data class BreedDetailsEntity(
    @PrimaryKey val id: String,
    var name: String,
    val lifeExpectancy: Int,
    var isFavorite: Boolean,
    val origin: String = EMPTY_STRING,
    val temperament: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val imageUrl: String = EMPTY_STRING,
)