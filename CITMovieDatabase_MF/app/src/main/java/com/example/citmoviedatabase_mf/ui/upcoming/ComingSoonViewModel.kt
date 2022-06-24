package com.example.citmoviedatabase_mf.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus
import kotlinx.coroutines.launch


class ComingSoonViewModel(private val comingSoonRepository: ComingSoonRepository) : ViewModel() {

    val status = MutableLiveData<ComingSoonViewModelStatus>()

    fun getAllMoviesUpcoming() {

        viewModelScope.launch {

            when (val response = comingSoonRepository.getAllMoviesUpcoming()) {
                is ComingSoonStatus.Success -> {
                    status.value = ComingSoonViewModelStatus.Success(response.listComingSoon)
                }
                is ComingSoonStatus.NotFound -> {
                    status.value = ComingSoonViewModelStatus.NotFound
                }
                is ComingSoonStatus.Error -> {
                    status.value = ComingSoonViewModelStatus.Error(response.error)
                }
            }
        }
    }
}