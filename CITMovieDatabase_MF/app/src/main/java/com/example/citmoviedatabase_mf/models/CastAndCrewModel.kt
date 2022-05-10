package com.example.citmoviedatabase_mf.models

import com.google.gson.annotations.SerializedName

data class CastAndCrewModel(
    val id: Int,
    @SerializedName("profile_path")
    val actorPicture : Int,
    @SerializedName("name")
    val actorName: String,
    val character: String
)
