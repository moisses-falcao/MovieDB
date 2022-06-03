package com.example.citmoviedatabase_mf.repository.comingsoon

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService

class ComingSoonRepositoryImpl(private val movieDatabaseService: MovieDatabaseService) : ComingSoonRepository {
    
    override suspend fun getAllMoviesUpcoming() : ComingSoonStatus {

        return try {
            val response = movieDatabaseService.getAllMoviesUpcoming()

            if (response.results.isNotEmpty()){
                ComingSoonStatus.Success(response)
            }else{
                ComingSoonStatus.NotFound
            }
        }catch(e: Exception){
            ComingSoonStatus.Error(e)
        }
    }
}