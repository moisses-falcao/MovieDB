package com.example.citmoviedatabase_mf.repository.nowplaying

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingRepositoryImpl(val movieDatabaseService: MovieDatabaseService): NowPlayingRepository{


    override fun getAllMoviesNowPlaying(nowPlayingStatus: (NowPlayingStatus) -> Unit) {

        movieDatabaseService.getAllMoviesNowPlaying().enqueue(object: Callback<Results>{
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if(response.isSuccessful){
                    nowPlayingStatus(NowPlayingStatus.Success(response.body()!!))
                }else{
                    nowPlayingStatus(NowPlayingStatus.NotFound)
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                nowPlayingStatus(NowPlayingStatus.Error(t))
            }
        })
    }
}

