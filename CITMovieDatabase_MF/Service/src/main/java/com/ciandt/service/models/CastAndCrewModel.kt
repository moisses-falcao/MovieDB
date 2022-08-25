package com.ciandt.service.models

import com.google.gson.annotations.SerializedName

data class CastAndCrewModel(
    val id: Int,
    @SerializedName("profile_path")
    val actorPicture : String?,
    @SerializedName("name")
    val actorName: String,
    val character: String
)
