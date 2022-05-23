package com.example.citmoviedatabase_mf.repository.casting

import androidx.lifecycle.LiveData

interface CastingRepository {

    fun getMovieCredits(movieId: String) : LiveData<CastingStatus>
}