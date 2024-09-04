package com.example.alexstarter.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alexstarter.domain.repository.MovieRepository
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    /*private val _movieDetailsState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailsState: StateFlow<MovieDetailState> = _movieDetailsState

    /*init {
        fetchMovieDetails(movieId = )
    }*/

    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            try {
                val movie = repository.getMovieDetails(movieId)  // Faites l'appel API ici
                _movieDetailsState.value = MovieDetailState.Loaded(movie)
            } catch (e: Exception) {
                _movieDetailsState.value = MovieDetailState.Error(e.message ?: "Unknown Error")
            }
        }
    }*/

    private val _movieDetailsState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailsState: StateFlow<MovieDetailState> = _movieDetailsState

    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieDetailsState.value = MovieDetailState.Loading
                    }
                    is Resource.Success -> {
                        resource.data?.let { movie ->
                            _movieDetailsState.value = MovieDetailState.Loaded(movie)
                        } ?: run {
                            _movieDetailsState.value = MovieDetailState.Error("Movie not found")
                        }
                    }
                    is Resource.Error -> {
                        _movieDetailsState.value = MovieDetailState.Error(resource.message ?: "Unknown error")
                    }
                }
            }
        }
    }

}