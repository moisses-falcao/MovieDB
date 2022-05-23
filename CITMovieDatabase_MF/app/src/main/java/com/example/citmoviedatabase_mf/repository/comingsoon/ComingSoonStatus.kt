package com.example.citmoviedatabase_mf.repository.comingsoon

import com.example.citmoviedatabase_mf.models.Results

sealed class ComingSoonStatus {
    object NotFound: ComingSoonStatus()
    data class Success(val listComingSoon: Results): ComingSoonStatus()
    data class Error(val error: Throwable): ComingSoonStatus()
}