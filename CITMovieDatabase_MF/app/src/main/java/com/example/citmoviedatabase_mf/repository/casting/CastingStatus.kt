package com.example.citmoviedatabase_mf.repository.casting

import com.ciandt.service.models.CastModel

sealed class CastingStatus {
    data class Success(val casting: CastModel): CastingStatus()
    data class Error(val error: Throwable): CastingStatus()
    object NotFound: CastingStatus()
}