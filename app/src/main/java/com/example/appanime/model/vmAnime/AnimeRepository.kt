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

    fun obtainAnimeinByID(nombre: String): LiveData<AnimeEnti>{
        return animeDao.getAnimeByID(nombre)
    }

    //Vieja Confiable
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


    //Corutines
    fun getDataFromServer() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.getDataFromAniCoru() }
        service.onSuccess {
            when(it.code()){
                in 200..299 -> it.body()?.let {
                    animeDao.insertAllAnime(converter(it.top))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
            service.onFailure {
                Log.e("ERROR", it.message.toString())
            }
        }
    }



    fun converter(listAnime: List<Top>): List<AnimeEnti>{
        var ani : MutableList<AnimeEnti> = mutableListOf<AnimeEnti>()
        listAnime.map {
            ani.add(AnimeEnti(it.title, it.imageUrl, it.url, it.startDate,
                it.endDate, it.episodes))
        }
        return ani
    }
}