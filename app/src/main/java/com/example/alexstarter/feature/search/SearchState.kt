package com.example.alexstarter.feature.search

sealed class SearchState {

    data class Error(val message: String) : SearchState()

    object Loading : SearchState()

    data class Loaded(val data: List<String>) : SearchState()

}