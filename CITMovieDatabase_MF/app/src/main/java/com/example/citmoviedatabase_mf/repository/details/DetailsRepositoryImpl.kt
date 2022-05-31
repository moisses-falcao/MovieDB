package com.example.citmoviedatabase_mf.repository.details

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.CastModel
import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.models.SceneModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : DetailsRepository {

    override fun getMovieDetails(movieId: String, detailsStatus: (DetailsStatus) -> Unit){

        movieDatabaseService.getMovieDetails(movieId).enqueue(object : Callback<MovieDetailsModel> {
            override fun onResponse(
                call: Call<MovieDetailsModel>,
                response: Response<MovieDetailsModel>
            ) {
                if(response.isSuccessful){
                    detailsStatus(DetailsStatus.SuccessDetails(response.body()!!))
                }else{
                    detailsStatus(DetailsStatus.NotFound)
                }
            }

            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                detailsStatus(DetailsStatus.Error(t))
            }
        })
    }

    override fun getMovieCredits(movieId: String, detailsStatus: (DetailsStatus) -> Unit){

        movieDatabaseService.getMovieCredits(movieId).enqueue(object: Callback<CastModel>{
            override fun onResponse(call: Call<CastModel>, response: Response<CastModel>) {
                if(response.isSuccessful){
                    detailsStatus(DetailsStatus.SuccessCredits(response.body()!!))
                }else{
                    detailsStatus(DetailsStatus.NotFound)
                }
            }

            override fun onFailure(call: Call<CastModel>, t: Throwable) {
                detailsStatus(DetailsStatus.Error(t))
            }
        })
    }

    override fun getMovieScenes(movieId: String, detailsStatus: (DetailsStatus) -> Unit){

        movieDatabaseService.getMovieScenes(movieId).enqueue(object: Callback<SceneModel>{
            override fun onResponse(call: Call<SceneModel>, response: Response<SceneModel>) {
                if (response.isSuccessful){
                    detailsStatus(DetailsStatus.SuccessScenes(response.body()!!))
                }else{
                    detailsStatus(DetailsStatus.NotFound)
                }
            }

            override fun onFailure(call: Call<SceneModel>, t: Throwable) {
                detailsStatus(DetailsStatus.Error(t))
            }
        })
    }
}