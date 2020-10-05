package com.example.appanime.model.vmAnime

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appanime.model.local.dao.AnimeDao
import com.example.appanime.model.local.entities.AnimeEnti
import com.example.appanime.model.remote.RetrofitClient
import com.example.appanime.model.remote.pojoAnime.Anime
import com.example.appanime.model.remote.pojoAnime.Top
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimeRepository(private val animeDao: AnimeDao) {

    private val service = RetrofitClient.retrofitClient()
    val mLiveData = animeDao.getAllAnimeFromDB()

    fun obtainTerrainByID(nombre: String): LiveData<AnimeEnti>{
        return animeDao.getAnimeByID(nombre)
    }

    fun obtainDataInternet(){
        service.getDataFromAni().enqueue(object : Callback<Anime>{

            override fun onResponse(call: Call<Anime>, response: Response<Anime>) {
                Log.d("REPO", response.body().toString())
                when(response.code()){
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch { response.body()?.
                    let { animeDao.insertAllAnime(converter(it.top))}}
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<Anime>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }
        })
    }

    fun converter(listAnime: List<Top>): List<AnimeEnti>{
        var ani : MutableList<AnimeEnti> = mutableListOf<AnimeEnti>()
        listAnime.map {
            ani.add(AnimeEnti(it.title, it.imageUrl, it.url, it.startDate,
                it.episodes))
        }
        return ani
    }
}