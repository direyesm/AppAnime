package com.example.appanime.model.vmManga

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appanime.model.local.AnimeRoomDatabase
import com.example.appanime.model.local.entities.MangaEnti

class MangaViewModel(application: Application): AndroidViewModel(application) {
    private  var mangaRepository : MangaRepository

    init {

        val mangaDao = AnimeRoomDatabase.getDatabase(application).getMangaDao()
        mangaRepository = MangaRepository(mangaDao)
        mangaRepository.obtanDataManga()
    }

    fun exposeLiDataFromDataBaseManga(): LiveData<List<MangaEnti>>{
        return mangaRepository.mLiveData
    }

    fun obtainMangainByID(nombre: String): LiveData<MangaEnti>{
        return mangaRepository.obtainMangainByID(nombre)
    }
}
