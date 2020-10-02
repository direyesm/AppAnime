package com.example.appanime

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appanime.model.AnimeViewModel
import com.example.appanime.model.local.entities.AnimeEnti
import com.example.appanime.model.remote.pojo.Anime
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),AnimeAdapter.AnimeSet {

    lateinit var mViedModel: AnimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViedModel = ViewModelProvider(this).get(AnimeViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = recyclerView
        val mAdapter = AnimeAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mViedModel.exposeLiveDataFromDataBase().observe(viewLifecycleOwner, Observer {
            Log.d("VIEW", it.toString())
            mAdapter.updateListAnime(it)
        })

    }

    override fun passAnimeSet(mAnime: AnimeEnti) {
        TODO("Not yet implemented")
    }

}