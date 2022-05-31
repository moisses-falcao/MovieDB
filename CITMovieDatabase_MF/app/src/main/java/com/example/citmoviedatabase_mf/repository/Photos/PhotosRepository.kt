package com.example.citmoviedatabase_mf.repository.Photos

import androidx.lifecycle.LiveData

interface PhotosRepository {

    fun getMovieScenes(movieId: String, photosStatus: (PhotosStatus) -> Unit)
}