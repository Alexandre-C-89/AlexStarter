package com.example.alexstarter.feature.search

import com.example.alexstarter.domain.model.Movie

sealed class SearchState {

    data class Error(val message: String) : SearchState()

    object Loading : SearchState()

    data class Loaded(val data: List<String>) : SearchState()

}