package com.droididiot.tmdbcompose.navigation

import MovieDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droididiot.tmdbcompose.data.Movie
import com.droididiot.tmdbcompose.ui.movielisting.MovieListScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movieList") {
        composable("movieList") {
            MovieListScreen(
                onMovieClick = { movie ->
                    val movieJson = URLEncoder.encode(Gson().toJson(movie), StandardCharsets.UTF_8.toString())
                    navController.navigate("movieDetail/$movieJson")
                }
            )
        }
        composable(
            route = "movieDetail/{movieJson}"
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movieJson")
            val decodedJson = movieJson?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
            val movie = Gson().fromJson(decodedJson, Movie::class.java)
            MovieDetailScreen(movie = movie) {
                navController.navigateUp()
            }
        }
    }
}
