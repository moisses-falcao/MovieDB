package com.example.citmoviedatabase_mf.ui.upcoming

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentComingSoonBinding
import com.ciandt.service.models.MovieModel
import com.example.citmoviedatabase_mf.ui.nowplaying.NowPlayingViewModelStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComingSoonFragment() : BaseFragment<FragmentComingSoonBinding, ComingSoonViewModel>() {

    override val viewModel: ComingSoonViewModel by viewModel()
    private lateinit var adapter: ComingSoonAdapter

    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComingSoonBinding = FragmentComingSoonBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        viewModel.getAllMoviesUpcoming()

        viewModel.status.observe(viewLifecycleOwner) {
            viewModel.getFavoriteList()

            when (it) {
                is ComingSoonViewModelStatus.Success -> {
                    adapter = ComingSoonAdapter(it.listComingSoon.results)
                    binding.rvComingSoon.adapter = adapter

                    lifecycleScope.launch {
                        viewModel.statusFavoriteList.collect { status ->

                            when (status) {
                                is ComingSoonViewModelStatus.SuccessFavoriteList -> {

                                    adapter.holdToFavorite { movieModel ->
                                        if (status.favoriteList.contains(movieModel)) {
                                            disfavorMovie(movieModel)
                                            viewModel.statusFavoriteList
                                        } else {
                                            favoriteMovie(movieModel)
                                        }
                                    }
                                }
                                is ComingSoonViewModelStatus.Error -> {
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
                is ComingSoonViewModelStatus.NotFound -> {
                    Toast.makeText(
                        context,
                        "Não foi possível carregar a lista de filmes",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ComingSoonViewModelStatus.Error -> {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG)
                }
                else -> {}
            }
        }
    }
}
