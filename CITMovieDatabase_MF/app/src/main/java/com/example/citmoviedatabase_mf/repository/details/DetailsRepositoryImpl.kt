package com.example.citmoviedatabase_mf.repository.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.CastModel
import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.models.SceneModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : DetailsRepository {

    override fun getMovieDetails(movieId: String): LiveData<DetailsStatus> {

        val status = MutableLiveData<DetailsStatus>()

        movieDatabaseService.getMovieDetails(movieId).enqueue(object : Callback<MovieDetailsModel> {
            override fun onResponse(
                call: Call<MovieDetailsModel>,
                response: Response<MovieDetailsModel>
            ) {
                if(response.isSuccessful){
                    status.value = response.body()?.let { DetailsStatus.SuccessDetails(it) }
                }else{
                    status.value = DetailsStatus.NotFound
                }
            }

            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                status.value = DetailsStatus.Error(t)
            }
        })

        return status
    }

    override fun getMovieCredits(movieId: String): LiveData<DetailsStatus> {

        val status = MutableLiveData<DetailsStatus>()

        movieDatabaseService.getMovieCredits(movieId).enqueue(object: Callback<CastModel>{
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                if(response.isSuccessful){
                    status.value = response.body()?.let { DetailsStatus.SuccessCredits(it) }
                }else{
                    status.value = DetailsStatus.NotFound
                }
            }

            override fun onFailure(call: Call<CastModel>, t: Throwable) {
                status.value = DetailsStatus.Error(t)
            }

        })

        return status
    }

    override fun getMovieScenes(movieId: String): LiveData<DetailsStatus> {

        val status = MutableLiveData<DetailsStatus>()

        movieDatabaseService.getMovieScenes(movieId).enqueue(object: Callback<SceneModel>{
            override fun onResponse(call: Call<SceneModel>, response: Response<SceneModel>) {
                if (response.isSuccessful){
                    status.value = response.body()?.let { DetailsStatus.SuccessScenes(it) }
                }else{
                    status.value = DetailsStatus.NotFound
                }
            }

            override fun onFailure(call: Call<SceneModel>, t: Throwable) {
                status.value = DetailsStatus.Error(t)
            }
        })

        return status
    }
}