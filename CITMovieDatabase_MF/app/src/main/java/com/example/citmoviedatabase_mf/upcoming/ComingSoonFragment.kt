package com.example.citmoviedatabase_mf.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.citmoviedatabase_mf.basefragment.BaseFragment
import com.example.citmoviedatabase_mf.databinding.FragmentComingSoonBinding
import com.example.citmoviedatabase_mf.nowplaying.NowPlayingAdapter
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComingSoonFragment() : BaseFragment<FragmentComingSoonBinding, ComingSoonViewModel>() {

    override val viewModel: ComingSoonViewModel by viewModel()

    override fun getViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentComingSoonBinding = FragmentComingSoonBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        viewModel.getAllMoviesUpcoming()

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                is ComingSoonViewModelStatus.Success ->{
                    binding.rvComingSoon.adapter = NowPlayingAdapter(it.listComingSoon.results)
                }
                is ComingSoonViewModelStatus.NotFound ->{
                    Toast.makeText(context, "Não foi possível carregar a lista de filmes", Toast.LENGTH_LONG).show()
                }
                is ComingSoonViewModelStatus.Error ->{
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG)
                }
            }
        }
    }
}