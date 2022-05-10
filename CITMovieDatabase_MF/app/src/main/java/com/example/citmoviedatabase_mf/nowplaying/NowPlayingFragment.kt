package com.example.citmoviedatabase_mf.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService.Companion.movieDatabaseService
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentNowPlayingBinding
import com.example.citmoviedatabase_mf.models.Results
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class NowPlayingFragment(override val viewModel: NowPlayingViewModel) : BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel>() {


    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNowPlayingBinding = FragmentNowPlayingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val call = movieDatabaseService.getAllMoviesNowPlaying()
//        call.enqueue(object: Callback, retrofit2.Callback<Results>{
//            override fun onResponse(call: Call<Results>, response: Response<Results>) {
//                val nowPlayingAdapter = response.body()?.results?.let{
//                    NowPlayingAdapter(it)
//                }
//                binding.rvNowPlaying.adapter = nowPlayingAdapter
//            }
//            override fun onFailure(call: Call<Results>, t: Throwable) {
//
//            }
//        })

        viewModel.results.observe(viewLifecycleOwner, {binding.rvNowPlaying.adapter = NowPlayingAdapter(it.results)})

        viewModel.getAllMoviesNowPlaying()
    }
}
