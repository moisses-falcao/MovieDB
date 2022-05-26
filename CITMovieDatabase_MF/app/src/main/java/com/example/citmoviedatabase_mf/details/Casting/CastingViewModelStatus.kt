package com.example.citmoviedatabase_mf.details.Casting

import com.example.citmoviedatabase_mf.models.CastModel

sealed class CastingViewModelStatus{
    data class Success(val casting: CastModel): CastingViewModelStatus()
    data class Error(val error: Throwable): CastingViewModelStatus()
    object NotFound: CastingViewModelStatus()
}
