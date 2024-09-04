package com.example.alexstarter.util

sealed class Screen(val route: String) {
    object Home : Screen("main")
    //object Details : Screen("movie")
}