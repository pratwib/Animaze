package com.pratwib.animaze.di

import android.app.Application
import androidx.room.Room
import com.pratwib.animaze.data.local.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, AnimeDatabase::class.java, "animaze.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: AnimeDatabase) = database.animeDao()
}