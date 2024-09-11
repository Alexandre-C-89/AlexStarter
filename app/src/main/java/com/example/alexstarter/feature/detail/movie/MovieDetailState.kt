package com.example.alexstarter.feature.detail.movie

import com.example.alexstarter.domain.movie.model.Movie

sealed class MovieDetailState{

    data class Error(val message:  String) : MovieDetailState()

    object Loading : MovieDetailState()

    data class Loaded(val movie: Movie) : MovieDetailState()

}