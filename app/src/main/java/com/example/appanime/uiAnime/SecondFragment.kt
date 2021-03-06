package com.example.appanime.uiAnime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.appanime.R
import com.example.appanime.model.vmAnime.AnimeViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var mViewModel : AnimeViewModel
    var mId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mId = arguments?.getString("id") ?: ""
            Log.d("SEGUNDO", mId.toString())
        }
        mViewModel = ViewModelProvider(this).get(AnimeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var web: String = ""

        mId.let {
            mViewModel.obtainTerrainByID(mId).observe(viewLifecycleOwner, Observer {
                Glide.with(view.context).load(it.image).centerCrop().into(imageView)
                mangatxt.text = it.nombre
                //btnUrl.text = it.web
                dateStarTxt.text = it.datestar
                dateFiniTxt.text = it.datefinish
                episodesTxt.text = it.cantepi.toString()
                web = it.web
            })
        }



        btnUrl.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(web)
                )
            )
        }

    }

}