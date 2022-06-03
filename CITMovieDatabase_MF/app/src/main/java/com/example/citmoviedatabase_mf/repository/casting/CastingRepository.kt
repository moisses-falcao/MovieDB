package com.example.citmoviedatabase_mf.repository.casting

interface CastingRepository {

    suspend fun getMovieCredits(movieId: String) : CastingStatus
}