package com.pratwib.animaze.data.repository

import com.pratwib.animaze.data.local.AnimeDao
import com.pratwib.animaze.data.local.AnimeEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(private val animeDao: AnimeDao) {
    fun getAllAnime() = animeDao.getAllAnime()
    fun getAllFavoriteAnime() = animeDao.getAllFavoriteAnime()
    fun getAnime(id: Int) = animeDao.getAnime(id)
    fun searchAnime(query: String) = animeDao.searchAnime(query)
    suspend fun insertAllAnime(anime: List<AnimeEntity>) = animeDao.insertAllAnime(anime)
    suspend fun updateFavoriteAnime(id: Int, isFavorite: Boolean) =
        animeDao.updateFavoriteAnime(id, isFavorite)
}