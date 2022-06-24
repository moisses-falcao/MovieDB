package com.example.citmoviedatabase_mf.ui.nowplaying

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.lifecycle.lifecycleScope
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentNowPlayingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.citmoviedatabase_mf.ui.activities.main.MainActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NowPlayingFragment() : BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel>() {

    override val viewModel: NowPlayingViewModel by viewModel()
    private lateinit var adapter: NowPlayingAdapter

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

    private fun favoriteMovie() {
        adapter.holdToFavorite { movieModel ->

            viewModel.favoriteMovie(movieModel)
            Toast.makeText(context, movieModel.title + " " + "adicionado à lista de favoritos com sucesso!", Toast.LENGTH_LONG).show()
        }
    }

    private fun disfavorMovie(){
        adapter.holdToFavorite { movieModel ->

            viewModel.disfavorMovie(movieModel)
            Toast.makeText(context, movieModel.title + " " + "removido da lista de favoritos!", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {

        viewModel.getAllMoviesNowPlaying()

        viewModel.status.observe(viewLifecycleOwner) {
            viewModel.getFavoriteList()

            when (it) {
                is NowPlayingViewModelStatus.Success -> {
                    adapter = NowPlayingAdapter(it.listNowPlaying.results)
                    binding.rvNowPlaying.adapter = adapter

                    adapter.holdToFavorite {movieModel ->
                        viewModel.statusFavoriteList.observe(viewLifecycleOwner){status ->

                            when(status){
                                is NowPlayingViewModelStatus.SuccessFavoriteList ->{

                                    if(status.favoriteList.contains(movieModel)){
                                        disfavorMovie()
                                    }else{
                                        favoriteMovie()
                                    }
                                }
                                is NowPlayingViewModelStatus.Error ->{
                                    Toast.makeText(context, movieModel.title + status.error.message, Toast.LENGTH_LONG).show()

                                }
                            }
                        }
                    }
                }
                is NowPlayingViewModelStatus.NotFound -> {
                    Toast.makeText(
                        context,
                        "Não foi possível carregar a lista de filmes",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NowPlayingViewModelStatus.Error -> {
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
