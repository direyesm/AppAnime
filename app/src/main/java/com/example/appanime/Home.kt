package com.example.appanime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.appanime.uiAnime.FirstFragment
import com.example.appanime.uiAnime.MainActivity
import com.example.appanime.uiManga.First2Fragment
import com.example.appanime.uiManga.Manga
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Glide.with(this).load("https://images.squarespace-cdn.com/content/v1/5a0dd6831f318dcf5130a0d5/1577462730976-8TYFRWZDS5BJE1UB60BA/ke17ZwdGBToddI8pDm48kIIWdAnyBSrZ5E6Gv7JXlDh7gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z4YTzHvnKhyp6Da-NYroOW3ZGjoBKy3azqku80C789l0k9kZPbuygN4RSDPe_G5PO_pbVb0jdkjHmk-MhSr8npod9fyhKaF6iH64GfT8sX2GQ/papers.co-aw28-yourname-night-anime-sky-illustration-art-36-3840x2400-4k-wallpaper.jpg")
            .centerCrop().into(Fondo)

        btnAnime.setOnClickListener {
            val intent =  Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

        btnManga.setOnClickListener {
            val intent = Intent(this, Manga::class.java)

        }
    }
}