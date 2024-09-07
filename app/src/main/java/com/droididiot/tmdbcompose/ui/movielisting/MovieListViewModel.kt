package com.droididiot.tmdbcompose.ui.movielisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droididiot.tmdbcompose.data.Movie
import com.droididiot.tmdbcompose.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState


    private var allMovies: List<Movie> = emptyList()

    init {
        loadTrendingMovies()
    }

    private fun loadTrendingMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                allMovies = repository.getTrendingMovies()
                _uiState.update { it.copy(isLoading = false, movies = allMovies) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _uiState.update { it.copy(movies = allMovies) }
        } else {
            val filteredMovies = allMovies.filter { movie ->
                movie.title.contains(query, ignoreCase = true) ||
                        movie.overview.contains(query, ignoreCase = true)
            }
            _uiState.update { it.copy(movies = filteredMovies) }
        }
    }
}