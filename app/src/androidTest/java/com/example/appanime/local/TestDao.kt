package com.example.appanime.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.appanime.model.local.AnimeRoomDatabase
import com.example.appanime.model.local.dao.AnimeDao
import com.example.appanime.model.local.entities.AnimeEnti
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mAnimeDao: AnimeDao
    private lateinit var db : AnimeRoomDatabase


    @Before
    fun setUp(){
        val contex = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(contex, AnimeRoomDatabase::class.java).build()
        mAnimeDao = db.getAnimeDao()
    }

    @After
    fun showDown(){
        db.close()
    }


    @Test
    fun insertListElementoAnime_happy_case() = runBlocking {
        //given
        val animeList = listOf(AnimeEnti("sss","dadas","asdasd",
            "asdasd","asdsad",0))
        //when
        mAnimeDao.insertAllAnime(animeList)
        //then
        mAnimeDao.getAllAnimeFromDB().observeForever {
            assertThat(it.isNotEmpty())
            assertThat(it[0].nombre).isEqualTo("sss")
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun obtainAnimeByID() = runBlocking {
        //given
        val id = "1"
        val animeList = listOf(AnimeEnti("sss","dadas","asdasd",
            "asdasd","asdsad",0))
        //when
        mAnimeDao.insertAllAnime(animeList)
        //then
        mAnimeDao.getAnimeByID(id).removeObserver {
            assertThat(it).isEqualTo(id)
        }

    }
}