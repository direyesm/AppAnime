package com.example.appanime.model.vmAnime

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appanime.model.local.AnimeRoomDatabase
import com.example.appanime.model.local.entities.AnimeEnti

class AnimeViewModel(application: Application): AndroidViewModel(application) {
    private  var animeRepository : AnimeRepository

    init {
        val animeDao = AnimeRoomDatabase.getDatabase(application).getAnimeDao()
        animeRepository = AnimeRepository(animeDao)
       // animeRepository.obtainDataInternet()
        animeRepository.getDataFromServer()
    }

    fun exposeLiveDataFromDataBase(): LiveData<List<AnimeEnti>>{
        return  animeRepository.mLiveData
    }

    fun obtainTerrainByID(nombre: String): LiveData<AnimeEnti>{
        return animeRepository.obtainAnimeinByID(nombre)
    }

}