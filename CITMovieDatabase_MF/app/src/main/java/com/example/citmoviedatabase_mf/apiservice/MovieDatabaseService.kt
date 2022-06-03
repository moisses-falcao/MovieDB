package com.example.citmoviedatabase_mf.apiservice

import com.example.citmoviedatabase_mf.constants.Constants.API_KEY
import com.example.citmoviedatabase_mf.models.*
import retrofit2.Call
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