package com.example.appanime.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appanime.model.local.entities.MangaEnti


@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllManga(listManga: List<MangaEnti>)

    @Query("SELECT * FROM manga_tablet")
    fun getAllMangaFromDB(): LiveData<List<MangaEnti>>

    @Query("SELECT * FROM manga_tablet WHERE nombre =:nombre")
    fun getMangaByID(nombre: String): LiveData<MangaEnti>
}