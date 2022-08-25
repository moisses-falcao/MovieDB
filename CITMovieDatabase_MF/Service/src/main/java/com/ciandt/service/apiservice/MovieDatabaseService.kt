package com.ciandt.service.apiservice

import com.ciandt.service.constants.Constants.API_KEY
import com.ciandt.service.models.CastModel
import com.ciandt.service.models.MovieDetailsModel
import com.ciandt.service.models.Results
import com.ciandt.service.models.SceneModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDatabaseService {

    @GET("now_playing?$API_KEY")
    suspend fun getAllMoviesNowPlaying() : Results

    @GET("upcoming?$API_KEY")
    suspend fun getAllMoviesUpcoming() : Results

    @GET("{movie_id}?$API_KEY")
    suspend fun getMovieDetails(@Path("movie_id")todoId: String) : MovieDetailsModel

    @GET("{movie_id}/credits?$API_KEY")
    suspend fun getMovieCredits(@Path("movie_id")todoId: String) : CastModel

    @GET("{movie_id}/images?$API_KEY")
    suspend fun getMovieScenes(@Path("movie_id")todoId: String) : SceneModel
}