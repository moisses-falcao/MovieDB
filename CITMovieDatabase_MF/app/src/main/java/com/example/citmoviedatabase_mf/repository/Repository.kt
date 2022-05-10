package com.example.citmoviedatabase_mf.repository

import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService

class Repository(private val movieDatabaseService: MovieDatabaseService) {

    fun getMovieDetails() = movieDatabaseService.getMovieDetails("")

    fun getAllMoviesPlayingNow() = movieDatabaseService.getAllMoviesNowPlaying()

    fun getAllMoviesUpcoming() = movieDatabaseService.getAllMoviesUpcoming()
}