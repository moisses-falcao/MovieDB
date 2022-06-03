package com.example.citmoviedatabase_mf.repository.Photos

import androidx.lifecycle.LiveData

interface PhotosRepository {

    suspend fun getMovieScenes(movieId: String) : PhotosStatus
}