package com.example.alexstarter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.alexstarter.feature.detail.MovieDetailRoute
import com.example.alexstarter.feature.main.HomeRoute
import com.example.alexstarter.util.Screen

@Composable
fun AppNavigation(

) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeRoute(navController)
        }
        composable(
            route = "movie/{movie_id}",
            arguments = listOf(navArgument("movie_id") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movie_id")
            movieId?.let {
                MovieDetailRoute(
                    movieId = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }

}