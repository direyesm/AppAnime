package com.example.appanime

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appanime.model.local.entities.AnimeEnti
import com.example.appanime.model.remote.pojo.Anime
import kotlinx.android.synthetic.main.anime_item_list.view.*

class AnimeAdapter(var mAnime: AnimeSet): RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    private var animeList = emptyList<AnimeEnti>()

    fun updateListAnime(mDatalist: List<AnimeEnti>){
        animeList = mDatalist
        Log.d("DATOS", animeList.size.toString())
        notifyDataSetChanged()
    }


    inner class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgAnime = itemView.imgAnime
        val txtAnime = itemView.txtName
        val algo = itemView.setOnClickListener {
            mAnime.passAnimeSet(animeList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.anime_item_list, parent,false)
        return AnimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.txtAnime.text = animeList[position].nombre
        Glide.with(holder.itemView.context).load(animeList[position].image).into(holder.imgAnime)
    }

    override fun getItemCount() = animeList.size


    interface AnimeSet{
        fun passAnimeSet(mAnime: AnimeEnti)
    }

}