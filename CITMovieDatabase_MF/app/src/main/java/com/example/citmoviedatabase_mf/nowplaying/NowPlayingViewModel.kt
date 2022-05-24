package com.example.citmoviedatabase_mf.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus

class NowPlayingViewModel(val nowPlayingRepository: NowPlayingRepository): ViewModel() {

    fun getAllMoviesNowPlaying() : LiveData<NowPlayingStatus> {
        return nowPlayingRepository.getAllMoviesNowPlaying()
    }
}