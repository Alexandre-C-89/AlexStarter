package com.example.alexstarter.data.repository.movie.mapper

import com.example.alexstarter.data.locale.movie.MovieEntity
import com.example.alexstarter.data.remote.di.RemoteModule.Companion.IMAGE_BASE_URL
import com.example.alexstarter.data.remote.movie.dto.MovieDto
import com.example.alexstarter.domain.movie.model.CastMember
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.domain.movie.model.MovieCredits

fun MovieDto.toEntity(
): MovieEntity {
    return MovieEntity(
        id = id,
        image = IMAGE_BASE_URL + posterPath,
        title = title,
        overview = overview,
        popularity = popularity,
        dateDeSortie = releaseDate,
        genres = genres?.joinToString(", ") { it.name } ?: "",
        status = status ?: "Unknown",
        moyenneDesVotes = voteAverage,
        decompteDesVotes = voteCount
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        image = IMAGE_BASE_URL + image,
        title = title,
        overview = overview,
        popularity = popularity,
        dateDeSortie = dateDeSortie,
        genres = genres.split(", "),
        status = status ?: "Unknown",
        moyenneDesVotes = moyenneDesVotes,
        decompteDesVotes = decompteDesVotes
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