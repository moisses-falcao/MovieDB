package com.example.citmoviedatabase_mf.repository.nowplaying

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import java.lang.Exception

class NowPlayingRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : NowPlayingRepository {


    override suspend fun getAllMoviesNowPlaying(): NowPlayingStatus {

        try {
            val response = movieDatabaseService.getAllMoviesNowPlaying()

            if (response.results.isNotEmpty()) {
                return NowPlayingStatus.Success(response)
            } else {
                return NowPlayingStatus.NotFound
            }
        }catch(e: Exception){
            return NowPlayingStatus.Error(e)
        }
    }
}

