package com.example.citmoviedatabase_mf.repository.casting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.CastModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastingRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : CastingRepository{

    override fun getMovieCredits(movieId: String): LiveData<CastingStatus> {

        val status = MutableLiveData<CastingStatus>()

        movieDatabaseService.getMovieCredits(movieId).enqueue(object : Callback<CastModel> {
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                if(response.isSuccessful){
                    status.value = response.body()?.let { CastingStatus.Success(it) }
                }else{
                    status.value = CastingStatus.NotFound
                }
            }

            override fun onFailure(call: Call<CastModel>, t: Throwable) {
                status.value = CastingStatus.Error(t)
            }
        })

        return status
    }
}