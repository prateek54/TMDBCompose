package com.droididiot.tmdbcompose.data

import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: TMDBApiService) {
    suspend fun getTrendingMovies(): List<Movie> {
        return api.getTrendingMovies("40aca775882dba64a2f9529bd7bd712b").results
    }
}