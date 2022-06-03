package com.example.citmoviedatabase_mf.nowplaying

import androidx.lifecycle.*
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import kotlinx.coroutines.launch

class NowPlayingViewModel(private val nowPlayingRepository: NowPlayingRepository): ViewModel() {

    val status = MutableLiveData<NowPlayingViewModelStatus>()

    fun getAllMoviesNowPlaying() {

        viewModelScope.launch {

            when(val response = nowPlayingRepository.getAllMoviesNowPlaying()){
                is NowPlayingStatus.Success ->{
                    status.value = NowPlayingViewModelStatus.Success(response.listNowPlaying)
                }
                is NowPlayingStatus.NotFound ->{
                    status.value = NowPlayingViewModelStatus.NotFound
                }
                is NowPlayingStatus.Error ->{
                    status.value = NowPlayingViewModelStatus.Error(response.error)
                }
            }
        }
    }
}


