package com.example.citmoviedatabase_mf.repository.Photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.SceneModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRepositoryImpl : PhotosRepository {

    val movieDatabaseService = MovieDatabaseService.movieDatabaseService

    override fun getMovieScenes(movieId: String): LiveData<PhotosStatus> {

        val status = MutableLiveData<PhotosStatus>()

        movieDatabaseService.getMovieScenes(movieId).enqueue(object: Callback<SceneModel>{
            override fun onResponse(call: Call<SceneModel>, response: Response<SceneModel>) {
                if(response.isSuccessful){
                    status.value = response.body()?.let { PhotosStatus.Success(it) }
                }else{
                    status.value = PhotosStatus.NotFound
                }
            }

            override fun onFailure(call: Call<SceneModel>, t: Throwable) {
                status.value = PhotosStatus.Error(t)
            }
        })

        return status
    }
}