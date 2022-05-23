package com.example.citmoviedatabase_mf.apiservice

import com.example.citmoviedatabase_mf.constants.Constants.API_KEY
import com.example.citmoviedatabase_mf.constants.Constants.BASE_URL
import com.example.citmoviedatabase_mf.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDatabaseService {

    @GET("now_playing?$API_KEY")
    fun getAllMoviesNowPlaying() : Call<Results>

    @GET("upcoming?$API_KEY")
    fun getAllMoviesUpcoming() : Call<Results>

    @GET("{movie_id}?$API_KEY")
    fun getMovieDetails(@Path("movie_id")todoId: String) : Call<MovieDetailsModel>

    @GET("{movie_id}/credits?$API_KEY")
    fun getMovieCredits(@Path("movie_id")todoId: String) : Call<CastModel>

    @GET("{movie_id}/images?$API_KEY")
    fun getMovieScenes(@Path("movie_id")todoId: String) : Call<SceneModel>

    companion object{
        val movieDatabaseService: MovieDatabaseService by lazy{
            val movieDatabaseService = Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

            movieDatabaseService.create(MovieDatabaseService::class.java)
        }
    }
}