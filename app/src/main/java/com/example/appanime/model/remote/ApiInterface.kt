package com.example.appanime.model.remote

import com.example.appanime.model.remote.pojoAnime.Anime
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("top/anime")
    fun getDataFromAni(): Call<Anime>

    @GET("top/manga")
    fun getDataFromManga(): Call<Anime>
}