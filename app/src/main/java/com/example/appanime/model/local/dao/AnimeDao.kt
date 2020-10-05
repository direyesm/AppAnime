package com.example.appanime.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appanime.model.local.entities.AnimeEnti


@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(listAnime: List<AnimeEnti>)

    @Query("SELECT * FROM anime_table")
    fun getAllAnimeFromDB(): LiveData<List<AnimeEnti>>

    @Query("SELECT * FROM anime_table WHERE nombre =:nombre")
    fun getAnimeByID(nombre: String): LiveData<AnimeEnti>


}