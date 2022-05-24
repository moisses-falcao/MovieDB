package com.example.citmoviedatabase_mf.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepositoryImpl
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepositoryImpl
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ComingSoonViewModel(private val comingSoonRepository: ComingSoonRepository): ViewModel() {

    fun getAllMoviesUpcoming(): LiveData<ComingSoonStatus>{
        return comingSoonRepository.getAllMoviesUpcoming()
    }
}