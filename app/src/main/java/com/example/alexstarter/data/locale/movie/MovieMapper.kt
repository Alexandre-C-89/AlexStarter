package com.example.alexstarter.data.locale.movie

import com.example.alexstarter.data.remote.di.RemoteModule.Companion.IMAGE_BASE_URL
import com.example.alexstarter.data.remote.dto.MovieDto
import com.example.alexstarter.domain.model.CastMember
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.domain.model.MovieCredits

fun MovieDto.toMovieEntity(
): MovieEntity {
    return MovieEntity(
        id = id,
        image = IMAGE_BASE_URL + poster_path,
        title = title,
        overview = overview,
        popularity = popularity,
        dateDeSortie = release_date,
        genres = genres?.joinToString(", ") { it.name } ?: "",
        status = status ?: "Unknown",
        moyenneDesVotes = vote_average,
    )
}

fun MovieEntity.toMovie(
): Movie {
    return Movie(
        id = id,
        image = IMAGE_BASE_URL + image,
        title = title,
        overview = overview,
        dateDeSortie = dateDeSortie,
        genres = genres.split(", "),
        status = status ?: "Unknown",
        moyenneDesVotes = moyenneDesVotes
    )
}

fun MovieCredits.toCastMembers(): List<CastMember> {
    return cast
        .filter { it.profilePath != null }
        .map { castMember ->
            CastMember(
                id = castMember.id,
                name = castMember.name,
                character = castMember.character,
                profilePath = IMAGE_BASE_URL + castMember.profilePath
            )
        }
}