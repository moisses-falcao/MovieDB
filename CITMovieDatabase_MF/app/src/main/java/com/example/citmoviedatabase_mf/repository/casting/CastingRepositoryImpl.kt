package com.example.citmoviedatabase_mf.repository.casting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.CastModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastingRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : CastingRepository{

    override fun getMovieCredits(movieId: String, castingStatus: (CastingStatus) -> Unit) {

        movieDatabaseService.getMovieCredits(movieId).enqueue(object : Callback<CastModel> {
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                if(response.isSuccessful){
                    castingStatus(CastingStatus.Success(response.body()!!))
                }else{
                    castingStatus(CastingStatus.NotFound)
                }
            }

            override fun onFailure(call: Call<CastModel>, t: Throwable) {
                castingStatus(CastingStatus.Error(t))
            }
        })
    }
}