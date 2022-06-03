package com.example.citmoviedatabase_mf.repository.details

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService

class DetailsRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : DetailsRepository {

    override suspend fun getMovieDetails(movieId: String): DetailsStatus {
        return try {
            val response = movieDatabaseService.getMovieDetails(movieId)

                DetailsStatus.SuccessDetails(response)
        }catch(e: Exception){
            DetailsStatus.Error(e)
        }
    }

    override suspend fun getMovieCredits(movieId: String): DetailsStatus {
        return try{
            val response = movieDatabaseService.getMovieCredits(movieId)

            if(response.cast.isNotEmpty()){
                DetailsStatus.SuccessCredits(response)
            }else{
                DetailsStatus.NotFound
            }
        }catch(e: Exception){
            DetailsStatus.Error(e)
        }
    }

    override suspend fun getMovieScenes(movieId: String): DetailsStatus {
        return try{
            val response = movieDatabaseService.getMovieScenes(movieId)

            if(response.scenarios.isNotEmpty()){
                DetailsStatus.SuccessScenes(response)
            }else{
                DetailsStatus.NotFound
            }
        }catch(e: Exception){
            DetailsStatus.Error(e)
        }
    }
}