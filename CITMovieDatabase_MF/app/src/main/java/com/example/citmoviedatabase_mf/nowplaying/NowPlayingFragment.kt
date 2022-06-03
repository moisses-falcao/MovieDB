package com.example.citmoviedatabase_mf.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentNowPlayingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayingFragment() : BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel>() {

    override val viewModel: NowPlayingViewModel by viewModel()

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

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        viewModel.getAllMoviesNowPlaying()

        viewModel.status.observe(viewLifecycleOwner) {
            when(it){
                is NowPlayingViewModelStatus.Success ->{
                    binding.rvNowPlaying.adapter = NowPlayingAdapter(it.listNowPlaying.results)
                }
                is NowPlayingViewModelStatus.NotFound ->{
                    Toast.makeText(context, "Não foi possível carregar a lista de filmes", Toast.LENGTH_LONG).show()
                }
                is NowPlayingViewModelStatus.Error ->{
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
//        viewModel.getAllMoviesNowPlaying().observe(viewLifecycleOwner, Observer {
//            when(it){
//                is NowPlayingStatus.Success -> {
//                    binding.rvNowPlaying.adapter = NowPlayingAdapter(it.listNowPlaying.results)
//                }
//                is NowPlayingStatus.NotFound -> {
//                    Toast.makeText(context, "Não foi possível carregar a lista de filmes", Toast.LENGTH_LONG).show()
//                }
//                is NowPlayingStatus.Error -> {
//                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        })
    }
}
