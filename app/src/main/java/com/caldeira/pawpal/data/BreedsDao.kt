package com.caldeira.pawpal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.caldeira.pawpal.DATABASE_BREEDS_TABLE
import com.caldeira.pawpal.model.BreedDetailsEntity

@Dao
interface BreedsDao {
    @Query("SELECT * FROM $DATABASE_BREEDS_TABLE")
    fun getAll(): List<BreedDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: BreedDetailsEntity)

    @Query("SELECT * FROM $DATABASE_BREEDS_TABLE WHERE id = :id")
    fun getBreedById(id: String): BreedDetailsEntity?

    @Query("UPDATE $DATABASE_BREEDS_TABLE SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: String, isFavorite: Boolean)

    @Query("SELECT EXISTS(SELECT 1 FROM $DATABASE_BREEDS_TABLE WHERE id = :id)")
    fun hasBreed(id: String): Boolean

    @Query("DELETE FROM $DATABASE_BREEDS_TABLE")
    fun deleteAll()
}