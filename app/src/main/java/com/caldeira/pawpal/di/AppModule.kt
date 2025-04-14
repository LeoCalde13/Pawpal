package com.caldeira.pawpal.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.caldeira.pawpal.data.BreedsDao
import com.caldeira.pawpal.data.BreedsDatabase
import com.caldeira.pawpal.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatsRepository(dao: BreedsDao): CatsRepository {
        return CatsRepository(dao)
    }

    @Provides
    @Singleton
    fun provideDefaultDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BreedsDatabase {
        return databaseBuilder(context, BreedsDatabase::class.java, "BreedsDatabase").build()
    }

    @Provides
    @Singleton
    fun provideCatDao(database: BreedsDatabase): BreedsDao {
        return database.getBreedsDao()
    }
}
