package com.example.citmoviedatabase_mf.repository.comingsoon

import com.ciandt.service.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.local.MovieDatabaseDAO
import com.ciandt.service.models.MovieModel
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus

class ComingSoonRepositoryImpl(private val movieDatabaseService: MovieDatabaseService, private val movieDatabaseDAO: MovieDatabaseDAO) : ComingSoonRepository {
    
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

    override suspend fun favoriteMovie(movieModel: MovieModel): ComingSoonStatus {
        return try {
            movieDatabaseDAO.favoriteMovie(movieModel)

            ComingSoonStatus.SuccessInsertOnFavorites
        } catch (e: Exception){
            ComingSoonStatus.Error(e)
        }
    }

    override suspend fun disfavorMovie(movieModel: MovieModel): ComingSoonStatus {
        return try {
            movieDatabaseDAO.disfavorMovie(movieModel)

            ComingSoonStatus.SuccessDeleteFromFavorites
        } catch (e: Exception){
            ComingSoonStatus.Error(e)
        }
    }

    override fun getFavoriteList(): ComingSoonStatus {
        return try {
            val response = movieDatabaseDAO.getFavoriteList()

            ComingSoonStatus.SuccessFavoriteList(response)
        }catch (e: Exception){
            ComingSoonStatus.Error(e)
        }
    }
}