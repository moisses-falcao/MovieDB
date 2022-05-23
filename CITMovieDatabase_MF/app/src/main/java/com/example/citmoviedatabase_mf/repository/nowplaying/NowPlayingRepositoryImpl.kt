package com.example.citmoviedatabase_mf.repository.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingRepositoryImpl: NowPlayingRepository{

    private val movieDatabaseService = MovieDatabaseService.movieDatabaseService

    fun getMovieDetails(id: String) = movieDatabaseService.getMovieDetails(id)

    fun getAllMoviesUpcoming() = movieDatabaseService.getAllMoviesUpcoming()

    ///////////////////////////////////////

    override fun getAllMoviesNowPlaying(): LiveData<NowPlayingStatus> {

        val status = MutableLiveData<NowPlayingStatus>()

        movieDatabaseService.getAllMoviesNowPlaying().enqueue(object: Callback<Results>{
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                if(response.isSuccessful){
                    status.value = response.body()?.let { NowPlayingStatus.Success(it) }
                }else{
                    status.value = NowPlayingStatus.NotFound
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                status.value = NowPlayingStatus.Error(t)
            }

        })
        return status
    }
}

