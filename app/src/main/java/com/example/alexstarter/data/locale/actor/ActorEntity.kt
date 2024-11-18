package com.example.alexstarter.data.locale.actor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor")
class ActorEntity(
    @PrimaryKey
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