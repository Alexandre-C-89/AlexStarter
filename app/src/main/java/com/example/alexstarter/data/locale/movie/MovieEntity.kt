package com.example.alexstarter.data.locale.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val title: String,
    /*val backdrop_path: String,
    val genre_ids: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,

    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,*/



    //val category: String,
)
