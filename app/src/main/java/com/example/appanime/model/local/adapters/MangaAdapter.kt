package com.example.appanime.model.local.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appanime.R
import com.example.appanime.model.local.entities.MangaEnti
import kotlinx.android.synthetic.main.manga_item_list.view.*

class MangaAdapter(var mManga: MangaEnti): RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {
    private var mangaList = emptyList<MangaEnti>()

    fun uodateListManga(mDatalist: List<MangaEnti>){
        mangaList = mDatalist
        Log.d("DATOS", mangaList.size.toString())
        notifyDataSetChanged()
    }

    inner class MangaViewHolder(itemView:  View): RecyclerView.ViewHolder(itemView){
        val imgManga = itemView.imgManga
        val txtManga = itemView.txtname
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.manga_item_list, parent, false)
        return  MangaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.txtManga.text = mangaList[position].nombre
        Glide.with(holder.itemView.context).load(mangaList[position].image).into(holder.imgManga)
    }

    override fun getItemCount() = mangaList.size


    interface  MangaSet{
        fun passMangSet(mManga: MangaEnti)
    }
}