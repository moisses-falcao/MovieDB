package com.example.citmoviedatabase_mf.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepositoryImpl
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus

class NowPlayingViewModel(private val nowPlayingRepository: NowPlayingRepository = NowPlayingRepositoryImpl()): ViewModel() {

    fun getAllMoviesNowPlaying() : LiveData<NowPlayingStatus> {
        return nowPlayingRepository.getAllMoviesNowPlaying()
    }
}