package com.droididiot.tmdbcompose.ui.movielisting

import com.droididiot.tmdbcompose.data.Movie

data class MovieListUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)