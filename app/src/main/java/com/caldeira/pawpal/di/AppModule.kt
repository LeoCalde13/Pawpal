package com.caldeira.pawpal.di

import com.caldeira.pawpal.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCatsRepository(): CatsRepository {
        return CatsRepository()
    }

    @Provides
    @Singleton
    fun provideDefaultDispatcher() = Dispatchers.IO
}
