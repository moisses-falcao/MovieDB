package com.example.citmoviedatabase_mf.repository.nowplaying

import com.ciandt.service.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.local.MovieDatabaseDAO
import com.ciandt.service.models.MovieModel


class NowPlayingRepositoryImpl(val movieDatabaseService: MovieDatabaseService, val movieDatabaseDAO: MovieDatabaseDAO) : NowPlayingRepository {


    override suspend fun getAllMoviesNowPlaying(): NowPlayingStatus {

        return try {
            val response = movieDatabaseService.getAllMoviesNowPlaying()

            if (response.results.isNotEmpty()) {
                NowPlayingStatus.Success(response)
            } else {
                NowPlayingStatus.NotFound
            }
        }catch(e: Exception){
            NowPlayingStatus.Error(e)
        }
    }

    override suspend fun favoriteMovie(movieModel: MovieModel): NowPlayingStatus {
        return try {
            movieDatabaseDAO.favoriteMovie(movieModel)

            NowPlayingStatus.SuccessInsertOnFavorites
        } catch (e: Exception){
            NowPlayingStatus.Error(e)
        }
    }

    override suspend fun disfavorMovie(movieModel: MovieModel): NowPlayingStatus {
        return try {
            movieDatabaseDAO.disfavorMovie(movieModel)

            NowPlayingStatus.SuccessDeleteFromFavorites
        } catch (e: Exception){
            NowPlayingStatus.Error(e)
        }
    }

    override fun getFavoriteList(): NowPlayingStatus {
        return try {
            val response = movieDatabaseDAO.getFavoriteList()

            NowPlayingStatus.SuccessFavoriteList(response)
        }catch (e: Exception){
            NowPlayingStatus.Error(e)
        }
    }
}

