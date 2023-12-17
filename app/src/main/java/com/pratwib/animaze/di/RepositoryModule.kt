package com.pratwib.animaze.di

import com.pratwib.animaze.data.local.AnimeDao
import com.pratwib.animaze.data.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(animeDao: AnimeDao) = AnimeRepository(animeDao)
}