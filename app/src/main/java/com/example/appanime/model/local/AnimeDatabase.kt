package com.example.appanime.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appanime.model.local.dao.AnimeDao
import com.example.appanime.model.local.dao.MangaDao
import com.example.appanime.model.local.entities.AnimeEnti
import com.example.appanime.model.local.entities.MangaEnti

@Database(entities = [AnimeEnti::class, MangaEnti::class], version = 1)
abstract class AnimeRoomDatabase : RoomDatabase(){

    abstract fun getAnimeDao(): AnimeDao
    abstract fun getMangaDao(): MangaDao

    companion object{
        @Volatile
        private var INSTANCE: AnimeRoomDatabase? = null

        fun getDatabase(context: Context): AnimeRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeRoomDatabase::class.java,
                    "anime_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }


}