package com.example.alexstarter.data.locale.movie

import com.example.alexstarter.ApiInterface.Companion.IMAGE_BASE_URL
import com.example.alexstarter.data.remote.dto.MovieDto
import com.example.alexstarter.domain.model.Movie

fun MovieDto.toMovieEntity(
): MovieEntity {
    return MovieEntity(
        id = id,
        image = IMAGE_BASE_URL + poster_path,
        title = title
    )
}

fun MovieEntity.toMovie(
): Movie {
    return Movie(
        id = id,
        image = IMAGE_BASE_URL + image,
        title = title
    )
}