package com.example.appanime.model.remote.pojoManga


import com.google.gson.annotations.SerializedName

data class Manga(
    @SerializedName("top")
    val top: List<Top>
)