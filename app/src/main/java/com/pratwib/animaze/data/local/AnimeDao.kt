package com.pratwib.animaze.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getAllFavoriteAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE id = :id")
    fun getAnime(id: Int): Flow<AnimeEntity>

    @Query("SELECT * FROM anime WHERE title LIKE '%' || :query || '%'")
    fun searchAnime(query: String): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(animeList: List<AnimeEntity>)

    @Query("UPDATE anime SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteAnime(id: Int, isFavorite: Boolean)
}