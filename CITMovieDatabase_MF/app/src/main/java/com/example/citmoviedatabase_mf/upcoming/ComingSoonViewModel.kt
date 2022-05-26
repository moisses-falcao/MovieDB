package com.example.citmoviedatabase_mf.upcoming

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus


class ComingSoonViewModel(private val comingSoonRepository: ComingSoonRepository): ViewModel() {

    val status = MediatorLiveData<ComingSoonViewModelStatus>()

    fun getAllMoviesUpcoming(){

        status.addSource(comingSoonRepository.getAllMoviesUpcoming()){
            when(it){
                is ComingSoonStatus.Success ->{
                    status.value = ComingSoonViewModelStatus.Success(it.listComingSoon)
                }
                is ComingSoonStatus.NotFound ->{
                    status.value = ComingSoonViewModelStatus.NotFound
                }
                is ComingSoonStatus.Error ->{
                    status.value = ComingSoonViewModelStatus.Error(it.error)
                }
            }
        }
    }
}