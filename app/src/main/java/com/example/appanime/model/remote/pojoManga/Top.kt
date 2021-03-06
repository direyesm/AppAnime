package com.example.appanime.model.remote.pojoManga


import com.google.gson.annotations.SerializedName

data class Top(
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("volumes")
    val volumes: Int
)