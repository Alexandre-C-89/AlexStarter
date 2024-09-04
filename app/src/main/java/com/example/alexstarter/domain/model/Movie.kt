package com.example.alexstarter.domain.model

data class Movie(
    val id: Int,
    val image: String,
    val title: String,
    val overview: String,
    val dateDeSortie: String,
    val genres: List<String>
)