package com.example.citmoviedatabase_mf.repository.details

interface DetailsRepository {

    fun getMovieDetails(movieId: String, detailsStatus: (DetailsStatus) -> Unit)

    fun getMovieCredits(movieId: String, detailsStatus: (DetailsStatus) -> Unit)

    fun getMovieScenes(movieId: String, detailsStatus: (DetailsStatus) -> Unit)
}