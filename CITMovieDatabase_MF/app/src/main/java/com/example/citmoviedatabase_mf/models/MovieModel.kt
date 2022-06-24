package com.example.citmoviedatabase_mf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.citmoviedatabase_mf.models.GenreModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieModel(

    @PrimaryKey
    val id: Int,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    //@SerializedName("genre_ids")
    //val genres: List<Int>? = emptyList()
)
