package com.example.citmoviedatabase_mf.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentNowPlayingBinding
import com.example.citmoviedatabase_mf.models.MovieModel

class NowPlayingFragment : BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel>() {

    override val viewModel: NowPlayingViewModel by viewModels()

    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNowPlayingBinding = FragmentNowPlayingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nowPlayingList = listOf<MovieModel>(
            MovieModel(0, 7.5,"John Wick 3","dsfgdfg",9.0, R.drawable.poster_john_wick, "fdgsf", "dfsgsdf", "22-10-2019", "Crime"),
            MovieModel(1, 9.5,"Capitain Marvel","dsfgdfg",9.0, R.drawable.poster_capita_marvel, "fdgsf", "dfsgsdf", "16/11/2019", "Action"),
            MovieModel(2, 6.7,"Alita","dsfgdfg",9.0, R.drawable.poster_alita, "fdgsf", "dfsgsdf", "30-09-2019", "Action"),
            MovieModel(3, 9.5,"Avangers","dsfgdfg",9.0, R.drawable.poster_avangers, "fdgsf", "dfsgsdf", "01-12-2019", "Action")
        )

        val nowPlayingAdapter by lazy{NowPlayingAdapter(nowPlayingList)}

        binding.rvNowPlaying.adapter = nowPlayingAdapter
    }


}
