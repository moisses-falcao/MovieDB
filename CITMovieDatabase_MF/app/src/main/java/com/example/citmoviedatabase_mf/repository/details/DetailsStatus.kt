package com.example.citmoviedatabase_mf.repository.details

import com.ciandt.service.models.CastModel
import com.ciandt.service.models.MovieDetailsModel
import com.ciandt.service.models.SceneModel

sealed class DetailsStatus {
    object NotFound: DetailsStatus()
    data class Error(val error: Throwable) : DetailsStatus()

    data class SuccessDetails(val movieDetails: MovieDetailsModel) : DetailsStatus()
    data class SuccessCredits(val casting: CastModel) : DetailsStatus()
    data class SuccessScenes(val scenes: SceneModel) : DetailsStatus()
}