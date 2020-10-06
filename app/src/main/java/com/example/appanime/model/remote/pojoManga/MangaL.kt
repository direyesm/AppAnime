package com.example.appanime.model.remote.pojoManga


import com.google.gson.annotations.SerializedName

data class MangaL(
    @SerializedName("top")
    val top: List<Top>
)