package com.example.citmoviedatabase_mf.repository.details

import com.example.citmoviedatabase_mf.models.CastModel
import com.example.citmoviedatabase_mf.models.MovieDetailsModel
import com.example.citmoviedatabase_mf.models.SceneModel

sealed class DetailsStatus {
    object NotFound: DetailsStatus()
    data class Error(val error: Throwable) : DetailsStatus()

    data class SuccessDetails(val movieDetails: MovieDetailsModel) : DetailsStatus()
    data class SuccessCredits(val casting: CastModel) : DetailsStatus()
    data class SuccessScenes(val scenes: SceneModel) : DetailsStatus()
}