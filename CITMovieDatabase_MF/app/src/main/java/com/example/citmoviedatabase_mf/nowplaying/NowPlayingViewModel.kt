package com.example.citmoviedatabase_mf.nowplaying

import androidx.lifecycle.*
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus

class NowPlayingViewModel(private val nowPlayingRepository: NowPlayingRepository): ViewModel() {

    val status = MutableLiveData<NowPlayingViewModelStatus>()

    fun getAllMoviesNowPlaying() {

        nowPlayingRepository.getAllMoviesNowPlaying(nowPlayingStatus = {
            when(it){
                is NowPlayingStatus.Success ->{
                    status.value = NowPlayingViewModelStatus.Success(it.listNowPlaying)
                }
                is NowPlayingStatus.NotFound ->{
                    status.value = NowPlayingViewModelStatus.NotFound
                }
                is NowPlayingStatus.Error ->{
                    status.value = NowPlayingViewModelStatus.Error(it.error)
                }
            }
        })
    }
}


