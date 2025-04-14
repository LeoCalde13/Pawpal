package com.caldeira.pawpal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.caldeira.pawpal.model.CatDetails

@Database(entities = [CatDetails::class], version = 1)
abstract class BreedsDatabase : RoomDatabase() {
    abstract fun getBreedsDao(): BreedsDao
}