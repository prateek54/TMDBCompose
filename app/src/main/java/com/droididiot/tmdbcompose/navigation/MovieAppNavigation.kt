package com.droididiot.tmdbcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droididiot.tmdbcompose.ui.movielisting.MovieListScreen

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movieList") {
        composable("movieList") {
            MovieListScreen(
                onMovieClick = { movie ->
//                   TODO() Open Movie Details Screen
                }
            )
        }
    }
}
