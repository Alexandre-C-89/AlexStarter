package com.example.alexstarter.feature.main

import com.example.alexstarter.domain.movie.model.Movie

sealed class HomeUiState {

    data class Error(val message:  String) : HomeUiState()

    object Loading : HomeUiState()

    data class Loaded(val transactions: List<Movie>) : HomeUiState()

}