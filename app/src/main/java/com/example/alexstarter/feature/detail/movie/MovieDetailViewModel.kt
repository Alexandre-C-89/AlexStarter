package com.example.alexstarter.feature.detail.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alexstarter.domain.movie.repository.MovieRepository
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _movieDetailsState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailsState: StateFlow<MovieDetailState> = _movieDetailsState

    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            val movieDetailsFlow = movieRepository.getMovieDetails(movieId)
            val movieCreditsFlow = movieRepository.getMovieCredits(movieId)
            combine(movieDetailsFlow, movieCreditsFlow) { movieDetailsResource, movieCreditsResource ->
                Log.d("FETCHMOVIEDETAILS", "MovieDetails: $movieDetailsResource, MovieCredits: $movieCreditsResource")
                if (movieDetailsResource is Resource.Success && movieCreditsResource is Resource.Success) {
                    val movie = movieDetailsResource.data?.copy(cast = movieCreditsResource.data ?: emptyList())
                    _movieDetailsState.value = if (movie != null) {
                        MovieDetailState.Loaded(movie)
                    } else {
                        MovieDetailState.Error("Movie not found")
                    }
                } else if (movieDetailsResource is Resource.Error || movieCreditsResource is Resource.Error) {
                    _movieDetailsState.value = MovieDetailState.Error("Error loading movie details or credits")
                } else {
                    _movieDetailsState.value = MovieDetailState.Loading
                }
            }.launchIn(viewModelScope)
        }
    }

    fun convertRatingToProgress(rating: Double): Float {
        return (rating / 10).toFloat()
    }

}