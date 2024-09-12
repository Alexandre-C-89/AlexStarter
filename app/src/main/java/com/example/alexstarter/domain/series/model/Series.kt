package com.example.alexstarter.domain.series.model

data class Series(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val created_by: List<String>,
    val episode_run_time: List<String>,
    val first_air_date: String,
    val genres: List<String>,
    val homepage: String?,
    val in_production: Boolean,
    val languages: List<String?>?,
    val last_air_date: String?,
    //val last_episode_to_air: List<String>,
    val name: String,
    val networks: List<String>,
    //val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<String>,
    val production_countries: List<String>,
    val seasons: List<String>,
    val spoken_languages: List<String>,
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double,
    val vote_count: Int
)