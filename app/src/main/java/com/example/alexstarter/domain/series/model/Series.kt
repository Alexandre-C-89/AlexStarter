package com.example.alexstarter.domain.series.model

import com.google.gson.annotations.SerializedName

data class Series(
    val id: Int,
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("created_by") val createdBy: List<String>,
    @SerializedName("episode_run_time") val episodeRunTime: List<String>,
    @SerializedName("first_air_date") val firstAirDate: String,
    val genres: List<String>,
    val homepage: String?,
    @SerializedName("in_production") val inProduction: Boolean,
    val languages: List<String?>?,
    @SerializedName("last_air_date") val lastAirDate: String?,
    //val last_episode_to_air: List<String>,
    val name: String,
    val networks: List<String>,
    //val next_episode_to_air: Any,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_name") val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: List<String>,
    @SerializedName("production_countries") val productionCountries: List<String>,
    val seasons: List<String>,
    @SerializedName("vote_average") val spokenLanguages: List<String>,
    val status: String?,
    val tagline: String?,
    val type: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)