package com.example.citmoviedatabase_mf.repository.nowplaying

import androidx.lifecycle.LiveData
import com.example.citmoviedatabase_mf.models.MovieModel

interface NowPlayingRepository {

    fun getAllMoviesNowPlaying() : LiveData<NowPlayingStatus>
}