package com.droididiot.tmdbcompose.data

import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApiService {
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String
    ): TrendingMoviesResponse
}