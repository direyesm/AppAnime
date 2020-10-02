package com.example.appanime.model.remote.pojo


import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("top")
    val top: List<Top>
)