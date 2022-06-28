package com.example.citmoviedatabase_mf.ui.nowplaying

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentNowPlayingBinding
import com.example.citmoviedatabase_mf.models.MovieModel
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    private fun favoriteMovie(movieModel: MovieModel) {

        viewModel.favoriteMovie(movieModel)

        Toast.makeText(
            context,
            movieModel.title + " " + "adicionado à lista de favoritos com sucesso!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun disfavorMovie(movieModel: MovieModel) {

        viewModel.disfavorMovie(movieModel)

        Toast.makeText(
            context,
            movieModel.title + " " + "removido da lista de favoritos!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupRecyclerView() {

        viewModel.getAllMoviesNowPlaying()

        viewModel.status.observe(viewLifecycleOwner) {
            viewModel.getFavoriteList()

            when (it) {
                is NowPlayingViewModelStatus.Success -> {
                    adapter = NowPlayingAdapter(it.listNowPlaying.results)
                    binding.rvNowPlaying.adapter = adapter


                        lifecycleScope.launch {
                            viewModel.statusFavoriteList.collect{ status ->

                                when (status) {
                                    is NowPlayingViewModelStatus.SuccessFavoriteList -> {

                                        adapter.holdToFavorite { movieModel ->
                                            if (status.favoriteList.contains(movieModel)) {
                                                disfavorMovie(movieModel)
                                                viewModel.statusFavoriteList
                                            } else {
                                                favoriteMovie(movieModel)
                                            }
                                        }
                                    }
                                    is NowPlayingViewModelStatus.Error -> {
                                        Log.e("ERRO", status.error.toString())
                                        Toast.makeText(
                                            context,
                                            status.error.message,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    else -> {

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
                else -> {}
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
