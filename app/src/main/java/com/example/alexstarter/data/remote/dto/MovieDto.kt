package com.example.alexstarter.data.remote.dto

data class MovieDto(
    val id: Int,
    val poster_path: String,
    val title: String,
    val overview: String,
    val release_date: String,
    val genres: List<GenreDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)