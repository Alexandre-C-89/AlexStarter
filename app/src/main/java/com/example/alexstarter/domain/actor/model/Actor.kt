package com.example.alexstarter.domain.actor.model

data class Actor(
    val id: Int,
    val adult: Boolean,
    //@SerializedName("also_known_as")val alsoKnownAs: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: String?,
    val gender: Int,
    val homepage: String?,
    //@SerializedName("imdb_id") val imdbId: String?,
    val knownForDepartment: String?,
    val name: String,
    val placeOfBirth: String?,
    val popularity: Double?,
    val profilePath: String?
)
