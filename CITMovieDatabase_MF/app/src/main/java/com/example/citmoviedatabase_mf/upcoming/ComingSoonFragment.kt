package com.example.citmoviedatabase_mf.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentComingSoonBinding
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.nowplaying.NowPlayingAdapter

class ComingSoonFragment(override val viewModel: ComingSoonViewModel) : BaseFragment<FragmentComingSoonBinding, ComingSoonViewModel>() {


    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComingSoonBinding = FragmentComingSoonBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.results.observe(viewLifecycleOwner, {binding.rvComingSoon.adapter = NowPlayingAdapter(it.results)})

        viewModel.getAllMoviesUpcoming()
    }
}