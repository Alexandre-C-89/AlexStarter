package com.example.alexstarter.data.repository.series

import com.example.alexstarter.data.locale.series.SeriesEntity
import com.example.alexstarter.data.remote.di.RemoteModule.Companion.IMAGE_BASE_URL
import com.example.alexstarter.data.remote.series.dto.SerieDto
import com.example.alexstarter.domain.series.model.Series

fun SerieDto.toSeriesEntity(
): SeriesEntity {
    return SeriesEntity(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        created_by = created_by?.joinToString(", ") { it.name } ?: "",
        episode_run_time = episode_run_time?.joinToString(", ") ?: "",
        first_air_date = first_air_date,
        genres = genres?.joinToString(", ") { it.name } ?: "",
        homepage = homepage,
        in_production = in_production,
        languages = languages?.joinToString(", ") ?: "",
        last_air_date = last_air_date,
        //last_episode_to_air = last_episode_to_air?.joinToString(", ") { it.name } ?: "",
        name = name,
        networks = networks?.joinToString(", ") { it.name } ?: "",
        //next_episode_to_air = next_episode_to_air,
        number_of_episodes = number_of_episodes,
        number_of_seasons = number_of_seasons,
        origin_country = origin_country?.joinToString(", ") ?: "",
        original_language = original_language,
        original_name = original_name,
        overview = overview,
        popularity = popularity,
        poster_path = IMAGE_BASE_URL + poster_path,
        production_companies = production_companies?.joinToString(", ") { it.name } ?: "",
        production_countries = production_countries?.joinToString(", ") { it.name } ?: "",
        seasons = seasons?.joinToString(", ") { it.name } ?: "",
        spoken_languages = spoken_languages?.joinToString(", ") { it.name } ?: "",
        status = status,
        tagline = tagline,
        type = type,
        vote_average = vote_average,
        vote_count = vote_count,
    )
}

fun SeriesEntity.toSeries(
): Series {
    return Series(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        created_by = created_by.split(", "),
        episode_run_time = episode_run_time.split(", "),
        first_air_date = first_air_date,
        genres = genres.split(", "),
        homepage = homepage,
        in_production = in_production,
        languages = languages?.split(", "),
        last_air_date = last_air_date,
        //last_episode_to_air = last_episode_to_air.split(", "),
        name = name,
        networks = networks.split(", "),
        //next_episode_to_air = next_episode_to_air,
        number_of_episodes = number_of_episodes,
        number_of_seasons = number_of_seasons,
        origin_country = origin_country.split(", "),
        original_language = original_language,
        original_name = original_name,
        overview = overview,
        popularity = popularity,
        poster_path = IMAGE_BASE_URL + poster_path,
        production_companies = production_companies.split(", "),
        production_countries = production_countries.split(", "),
        seasons = seasons.split(", "),
        spoken_languages = spoken_languages.split(", "),
        status = status,
        tagline = tagline,
        type = type,
        vote_average = vote_average,
        vote_count = vote_count,
    )
}