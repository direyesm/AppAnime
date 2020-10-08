package com.example.appanime.uiManga

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appanime.R
import com.example.appanime.model.local.adapters.MangaAdapter
import com.example.appanime.model.local.entities.MangaEnti
import com.example.appanime.model.vmManga.MangaViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first2.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class First2Fragment : Fragment(), MangaAdapter.MangaSet {

    lateinit var  mViewModel: MangaViewModel
    lateinit var adapter: MangaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MangaViewModel::class.java)
        adapter = MangaAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView = recyclerViewManga
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = GridLayoutManager(context,3)
        mRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        mRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL))


        mViewModel.exposeLiDataFromDataBaseManga().observe(viewLifecycleOwner, Observer {
            adapter.uodateListManga(it)
        })


    }

    override fun passMangaSet(mManga: MangaEnti) {
        val mBundle = Bundle()
        mBundle.putString("id", mManga.nombre)
        findNavController().navigate(R.id.action_first2Fragment_to_second2Fragment, mBundle)
    }

}