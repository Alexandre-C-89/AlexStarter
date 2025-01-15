package com.example.alexstarter.util

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MovieDetails : Screen("movie")
    object SeriesDetails : Screen("tv")
    object Search : Screen("search")
    object Actor : Screen("actor")
}