package com.example.appanime.uiManga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.appanime.R
import com.example.appanime.model.vmManga.MangaViewModel
import kotlinx.android.synthetic.main.fragment_second.imageView
import kotlinx.android.synthetic.main.fragment_second2.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Second2Fragment : Fragment() {

    lateinit var mViewModel: MangaViewModel
    var mId: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mId = arguments?.getString("id") ?: ""
        }
        mViewModel = ViewModelProvider(this).get(MangaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mId.let {
            mViewModel.obtainMangainByID(mId).observe(viewLifecycleOwner, Observer {
                Glide.with(view.context).load(it.image).centerCrop().into(imageView)
                mangatxt.text = it.nombre
            })
        }
    }
}