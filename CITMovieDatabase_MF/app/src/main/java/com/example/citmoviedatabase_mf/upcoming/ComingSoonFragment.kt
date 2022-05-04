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

class ComingSoonFragment : BaseFragment<FragmentComingSoonBinding, ComingSoonViewModel>() {
    override val viewModel: ComingSoonViewModel by viewModels()

    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComingSoonBinding = FragmentComingSoonBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nowPlayingList = listOf<MovieModel>(
            MovieModel(4, 6.5,"Avangers: EndGame","dsfgdfg",9.0, R.drawable.poster_end_game, "fdgsf", "dfsgsdf", "20-01-2020", "Action"),
            MovieModel(5, 4.5,"Blade Runner","dsfgdfg",9.0, R.drawable.poster_blade_runner, "fdgsf", "dfsgsdf", "22-01-2020", "Drama"),
            MovieModel(6, 8.5,"Joker","dsfgdfg",9.0, R.drawable.poster_joker, "fdgsf", "dfsgsdf", "27-02-2020", "Drama"),
            MovieModel(7, 9.5,"Us","dsfgdfg",9.0, R.drawable.poster_us, "fdgsf", "dfsgsdf", "12-02-2020", "Thriller")
        )

        val comingSoonAdapter by lazy{ NowPlayingAdapter(nowPlayingList) }

        binding.rvComingSoon.adapter = comingSoonAdapter
    }
}