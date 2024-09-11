package com.example.alexstarter.domain.movie.model

data class MovieCredits(
    val id: Int,
    val cast: List<CastMember>,
)
