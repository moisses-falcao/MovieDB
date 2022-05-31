package com.example.citmoviedatabase_mf.repository.Photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.SceneModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : PhotosRepository {


    override fun getMovieScenes(movieId: String, photosStatus: (PhotosStatus) -> Unit){

        movieDatabaseService.getMovieScenes(movieId).enqueue(object: Callback<SceneModel>{
            override fun onResponse(call: Call<SceneModel>, response: Response<SceneModel>) {
                if(response.isSuccessful){
                    photosStatus(PhotosStatus.Success(response.body()!!))
                }else{
                    photosStatus(PhotosStatus.NotFound)
                }
            }

            override fun onFailure(call: Call<SceneModel>, t: Throwable) {
                photosStatus(PhotosStatus.Error(t))
            }
        })
    }
}