package com.example.alexstarter.data.remote.series.dto

import com.example.alexstarter.domain.series.model.CreatedBy
import com.example.alexstarter.domain.series.model.Genre
import com.example.alexstarter.domain.series.model.Network
import com.example.alexstarter.domain.series.model.ProductionCompany
import com.example.alexstarter.domain.series.model.ProductionCountry
import com.example.alexstarter.domain.series.model.Season
import com.example.alexstarter.domain.series.model.SpokenLanguage

data class SerieDto(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<Genre>,
    val homepage: String?,
    val in_production: Boolean,
    val languages: List<String?>?,
    val last_air_date: String?,
    //val last_episode_to_air: List<LastEpisodeToAir>,
    val name: String,
    val networks: List<Network>,
    //val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double,
    val vote_count: Int
)