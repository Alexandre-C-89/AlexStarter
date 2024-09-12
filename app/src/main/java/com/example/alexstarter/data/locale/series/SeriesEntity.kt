package com.example.alexstarter.data.locale.series

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SeriesEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val created_by: String,
    val episode_run_time: String,
    val first_air_date: String,
    val genres: String,
    val homepage: String?,
    val in_production: Boolean,
    val languages: String?,
    val last_air_date: String?,
    //val last_episode_to_air: String,
    val name: String,
    val networks: String,
    //val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: String,
    val production_countries: String,
    val seasons: String,
    val spoken_languages: String,
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double,
    val vote_count: Int
)