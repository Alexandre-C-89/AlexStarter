package com.example.alexstarter.domain.movie.model

data class Movie(
    val id: Int,
    val image: String,
    val title: String,
    val overview: String,
    val dateDeSortie: String,
    val genres: List<String>,
    val status: String,
    val moyenneDesVotes: Double,
    val cast: List<CastMember> = emptyList(),
)