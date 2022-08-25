package com.ciandt.service.models

import com.google.gson.annotations.SerializedName

data class SceneModel(
    @SerializedName("backdrops")
    val scenarios : List<PhotoModel>
)
