package com.example.citmoviedatabase_mf.repository.casting

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService

class CastingRepositoryImpl(val movieDatabaseService: MovieDatabaseService) : CastingRepository{

    override suspend fun getMovieCredits(movieId: String) : CastingStatus {

        return try {
            val response = movieDatabaseService.getMovieCredits(movieId)

            if(response.cast.isNotEmpty()){
                CastingStatus.Success(response)
            }else{
                CastingStatus.NotFound
            }
        }catch(e: Exception){
            CastingStatus.Error(e)
        }
    }
}