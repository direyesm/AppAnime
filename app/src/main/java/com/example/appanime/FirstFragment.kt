package com.example.appanime

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import com.airbnb.lottie.LottieAnimationView
import com.example.appanime.model.AnimeViewModel
import com.example.appanime.model.local.entities.AnimeEnti
import com.example.appanime.model.remote.pojo.Anime
import kotlinx.android.synthetic.main.anime_item_list.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),AnimeAdapter.AnimeSet {

    lateinit var mViedModel: AnimeViewModel
    lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViedModel = ViewModelProvider(this).get(AnimeViewModel::class.java)
        adapter = AnimeAdapter(this)
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
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mViedModel.exposeLiveDataFromDataBase().observe(viewLifecycleOwner, Observer {
            Log.d("VIEW", it.toString())
            adapter.updateListAnime(it)
        })
//        var like = false
//        LikeImageView.setOnClickListener {
//            like = likeAnimation(LikeImageView, R.raw.bandai_dokkan, like)
//        }
    }

    override fun passAnimeSet(mAnime: AnimeEnti) {
        val mBundle = Bundle()
        mBundle.putString("id", mAnime.nombre)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)


    }
 //  private fun likeAnimation(imageView: LottieAnimationView, animation: Int, like: Boolean) : Boolean {
//
//        if (!like) {
//            imageView.setAnimation(animation)
//            imageView.playAnimation()
//        } else {
//            imageView.animate()
//                .alpha(0f)
//                .setDuration(200)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animator: Animator) {
//                        imageView.setImageResource(R.drawable.twitter_like)
//                        imageView.alpha = 1f
//                    }
//                })
//        }
//        return !like
//    }
}