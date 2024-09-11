package com.example.alexstarter.data.locale.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alexstarter.domain.model.CastMember

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val image: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val dateDeSortie: String,
    val genres: String,
    val status: String?,
    val moyenneDesVotes: Double
)
