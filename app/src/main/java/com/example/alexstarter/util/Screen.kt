package com.example.alexstarter.util

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object MovieDetails : Screen("Movie")
    object SeriesDetails : Screen("Tv")
    object Search : Screen("Search")
    object Actor : Screen("Actor")
}