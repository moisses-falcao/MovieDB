package com.example.citmoviedatabase_mf.repository.comingsoon

import androidx.lifecycle.LiveData

interface ComingSoonRepository {
    fun getAllMoviesUpcoming(): LiveData<ComingSoonStatus>
}