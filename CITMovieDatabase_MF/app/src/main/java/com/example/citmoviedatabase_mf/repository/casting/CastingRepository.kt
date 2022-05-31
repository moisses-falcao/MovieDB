package com.example.citmoviedatabase_mf.repository.casting

interface CastingRepository {

    fun getMovieCredits(movieId: String, castingStatus: (CastingStatus) -> Unit)
}