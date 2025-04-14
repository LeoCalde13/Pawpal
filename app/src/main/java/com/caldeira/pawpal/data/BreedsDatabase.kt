package com.caldeira.pawpal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.caldeira.pawpal.model.BreedDetailsEntity

@Database(entities = [BreedDetailsEntity::class], version = 1)
abstract class BreedsDatabase : RoomDatabase() {
    abstract fun getBreedsDao(): BreedsDao
}