package com.example.alexstarter.data.remote.movie.dto

data class MovieDto(
    val id: Int,
    val poster_path: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val genres: List<GenreDto>,
    val status: String,
    val vote_average: Double,
    val vote_count: Double
)

data class GenreDto(
    val id: Int,
    val name: String
)