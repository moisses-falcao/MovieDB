package com.example.citmoviedatabase_mf.repository.comingsoon

interface ComingSoonRepository {

    suspend fun getAllMoviesUpcoming() : ComingSoonStatus
}