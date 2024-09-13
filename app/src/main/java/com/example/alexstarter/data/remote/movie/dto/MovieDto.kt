package com.example.alexstarter.data.remote.movie.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("release_date")val releaseDate: String,
    val genres: List<GenreDto>,
    val status: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Double
)

data class GenreDto(
    val id: Int,
    val name: String
)