package com.example.citmoviedatabase_mf.apiservice

import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDatabaseService {

    @GET("now_playing?api_key=c98557c74b15f9613468213223de30bf")
    fun getAllMoviesNowPlaying() : Call<Results>

    @GET("upcoming?api_key=c98557c74b15f9613468213223de30bf")
    fun getAllMoviesUpcoming() : Call<Results>

    @GET("{movie_id}?api_key=c98557c74b15f9613468213223de30bf")
    fun getMovieDetails(@Path("movie_id")todoId: String) : Call<MovieDetailsModel>

    companion object{
        val movieDatabaseService by lazy{
            val movieDatabaseService = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/").addConverterFactory(GsonConverterFactory.create())
                .build()

            movieDatabaseService.create(MovieDatabaseService::class.java)
        }
    }

}