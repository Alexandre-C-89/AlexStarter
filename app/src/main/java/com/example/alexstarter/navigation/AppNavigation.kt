package com.example.alexstarter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.alexstarter.feature.actor.ActorRoute
import com.example.alexstarter.feature.detail.movie.MovieDetailRoute
import com.example.alexstarter.feature.detail.series.SeriesDetailRoute
import com.example.alexstarter.feature.main.HomeRoute
import com.example.alexstarter.feature.search.SearchRoute
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
            HomeRoute(
                navController
                /*onNavigateClick = { route ->
                    navController.navigate(route)
                }*/
            )
        }
        composable(Screen.Search.route) {
            SearchRoute(
                navController,
                onNavigateClick = { route ->
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = "${Screen.MovieDetails.route}/{movie_id}",
            arguments = listOf(navArgument("movie_id") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movie_id")
            movieId?.let {
                MovieDetailRoute(
                    navController = navController,
                    movieId = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
        composable(
            route = "${Screen.Actor.route}/{actor_id}",
            arguments = listOf(navArgument("actor_id") { type = NavType.StringType })
        ) { backStackEntry ->
            val actorId = backStackEntry.arguments?.getString("actor_id")
            actorId?.let {
                ActorRoute(
                    actorId = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
        composable(
            route = "${Screen.SeriesDetails.route}/{series_id}",
            arguments = listOf(navArgument("series_id") { type = NavType.StringType })
        ) { backStackEntry ->
            val seriesId = backStackEntry.arguments?.getString("series_id")
            seriesId?.let {
                SeriesDetailRoute(
                    seriesId = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }

}