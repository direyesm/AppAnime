package com.example.appanime.model.vmManga

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appanime.model.local.dao.MangaDao
import com.example.appanime.model.local.entities.MangaEnti
import com.example.appanime.model.remote.RetrofitClient
import com.example.appanime.model.remote.pojoManga.MangaL
import com.example.appanime.model.remote.pojoManga.Top
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MangaRepository(private val mangaDao: MangaDao) {


    private val service = RetrofitClient.retrofitClient()
    val mLiveData = mangaDao.getAllMangaFromDB()

    fun obtainMangainByID(nombre: String): LiveData<MangaEnti> {
        return mangaDao.getMangaByID(nombre)
    }

    fun obtanDataManga() {
        service.getDataFromManga().enqueue(object : Callback<MangaL>{
            override fun onResponse(call: Call<MangaL>, response: Response<MangaL>) {
                Log.d("REPO", response.body().toString())
                when(response.code()){
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch { response.body()?.
                    let {mangaDao.insertAllManga(MangaConverter(it.top))}}
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<com.example.appanime.model.remote.pojoManga.MangaL>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }

        })
    }
    fun MangaConverter(listManga: List<Top>): List<MangaEnti>{
        var man : MutableList<MangaEnti> = mutableListOf<MangaEnti>()
        listManga.map {
            man.add(MangaEnti(it.title, it.imageUrl)
            )
        }
        return man
    }
}
