package com.example.citmoviedatabase_mf.repository.nowplaying

import androidx.lifecycle.LiveData

interface NowPlayingRepository {

    fun getAllMoviesNowPlaying() : LiveData<NowPlayingStatus>
}