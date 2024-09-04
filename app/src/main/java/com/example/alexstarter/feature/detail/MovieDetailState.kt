package com.example.alexstarter.feature.detail

import com.example.alexstarter.domain.model.Movie

sealed class MovieDetailState(){

    data class Error(val message:  String) : MovieDetailState()

    object Loading : MovieDetailState()

    data class Loaded(val movie: Movie) : MovieDetailState()

}