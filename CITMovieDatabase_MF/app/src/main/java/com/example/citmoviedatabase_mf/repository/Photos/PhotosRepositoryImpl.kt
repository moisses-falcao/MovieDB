package com.example.citmoviedatabase_mf.repository.Photos

import com.ciandt.service.apiservice.MovieDatabaseService
import java.lang.Exception

class PhotosRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : PhotosRepository {


    override suspend fun getMovieScenes(movieId: String) : PhotosStatus{

        return try {
            val response = movieDatabaseService.getMovieScenes(movieId)

            if(response.scenarios.isNotEmpty()){
                PhotosStatus.Success(response)
            }else{
                PhotosStatus.NotFound
            }
        }catch(e: Exception){
            PhotosStatus.Error(e)
        }
    }
}