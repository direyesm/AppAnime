package com.example.appanime.uiManga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appanime.R

class Manga : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga)
        setSupportActionBar(findViewById(R.id.toolbar))

        }
    }
