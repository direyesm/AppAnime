package com.example.appanime.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Manga_tablet")
data class MangaEnti (@PrimaryKey val nombre: String,
                      val image: String
)