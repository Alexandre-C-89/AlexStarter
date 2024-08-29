package com.example.alexstarter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alexstarter.feature.main.HomeRoute
import com.example.alexstarter.util.Screen

@Composable
fun AppNavigation(

){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.rout
    ) {
       composable(Screen.Home.rout){
           HomeRoute(
           )
       }
    }

}