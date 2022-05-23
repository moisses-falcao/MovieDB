package com.example.citmoviedatabase_mf.repository.details

import androidx.lifecycle.LiveData

interface DetailsRepository {

    fun getMovieDetails(movieId: String) : LiveData<DetailsStatus>

    fun getMovieCredits(movieId: String) : LiveData<DetailsStatus>

    fun getMovieScenes(movieId: String) : LiveData<DetailsStatus>
}