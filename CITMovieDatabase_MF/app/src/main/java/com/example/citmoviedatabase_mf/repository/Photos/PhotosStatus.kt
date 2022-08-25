package com.example.citmoviedatabase_mf.repository.Photos

import com.ciandt.service.models.SceneModel

sealed class PhotosStatus{
    data class Success(val scenes: SceneModel) : PhotosStatus()
    data class Error(val error: Throwable) : PhotosStatus()
    object NotFound : PhotosStatus()
}
