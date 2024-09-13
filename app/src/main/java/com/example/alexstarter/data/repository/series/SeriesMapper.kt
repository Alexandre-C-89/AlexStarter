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
        backdrop_path = backdropPath,
        created_by = createdBy?.joinToString(", ") { it.name } ?: "",
        episode_run_time = episodeRunTime?.joinToString(", ") ?: "",
        first_air_date = firstAirDate,
        genres = genres?.joinToString(", ") { it.name } ?: "",
        homepage = homepage,
        in_production = inProduction,
        languages = languages?.joinToString(", ") ?: "",
        last_air_date = lastAirDate,
        //last_episode_to_air = last_episode_to_air?.joinToString(", ") { it.name } ?: "",
        name = name,
        networks = networks?.joinToString(", ") { it.name } ?: "",
        //next_episode_to_air = next_episode_to_air,
        number_of_episodes = numberOfEpisodes,
        number_of_seasons = numberOfSeasons,
        origin_country = originCountry?.joinToString(", ") ?: "",
        original_language = originalLanguage,
        original_name = originalName,
        overview = overview,
        popularity = popularity,
        poster_path = IMAGE_BASE_URL + poster_path,
        production_companies = productionCompanies?.joinToString(", ") { it.name } ?: "",
        production_countries = productionCountries?.joinToString(", ") { it.name } ?: "",
        seasons = seasons?.joinToString(", ") { it.name } ?: "",
        spoken_languages = spokenLanguages?.joinToString(", ") { it.name } ?: "",
        status = status,
        tagline = tagline,
        type = type,
        vote_average = voteAverage,
        vote_count = voteCount,
    )
}

fun SeriesEntity.toSeries(
): Series {
    return Series(
        id = id,
        adult = adult,
        backdropPath = backdrop_path,
        createdBy = created_by.split(", "),
        episodeRunTime = episode_run_time.split(", "),
        firstAirDate = first_air_date,
        genres = genres.split(", "),
        homepage = homepage,
        inProduction = in_production,
        languages = languages?.split(", "),
        lastAirDate = last_air_date,
        //last_episode_to_air = last_episode_to_air.split(", "),
        name = name,
        networks = networks.split(", "),
        //next_episode_to_air = next_episode_to_air,
        numberOfEpisodes = number_of_episodes,
        numberOfSeasons = number_of_seasons,
        originCountry = origin_country.split(", "),
        originalLanguage = original_language,
        originalName = original_name,
        overview = overview,
        popularity = popularity,
        posterPath = IMAGE_BASE_URL + poster_path,
        productionCompanies = production_companies.split(", "),
        productionCountries = production_countries.split(", "),
        seasons = seasons.split(", "),
        spokenLanguages = spoken_languages.split(", "),
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = vote_average,
        voteCount = vote_count,
    )
}