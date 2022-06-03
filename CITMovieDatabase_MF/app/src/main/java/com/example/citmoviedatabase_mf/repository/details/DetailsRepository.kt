package com.example.citmoviedatabase_mf.repository.details

interface DetailsRepository {

    suspend fun getMovieDetails(movieId: String) : DetailsStatus

    suspend fun getMovieCredits(movieId: String) : DetailsStatus

    suspend fun getMovieScenes(movieId: String) : DetailsStatus
}