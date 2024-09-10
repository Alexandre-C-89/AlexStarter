package com.example.alexstarter.util

sealed class Screen(val route: String) {
    object Home : Screen("main")
    object MovieDetails : Screen("movie")
    object SeriesDetails : Screen("tv")
}