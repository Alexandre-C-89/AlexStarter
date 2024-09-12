package com.example.alexstarter.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.domain.movie.repository.MovieRepository
import com.example.alexstarter.domain.series.model.Series
import com.example.alexstarter.domain.series.repository.SeriesRepository
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository
): ViewModel() {

    private val _moviesPopular = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val moviesPopular: StateFlow<Resource<List<Movie>>> = _moviesPopular

    private val _moviesUpcoming = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val moviesUpcoming: StateFlow<Resource<List<Movie>>> = _moviesUpcoming

    private val _seriesPopularState = MutableStateFlow<Resource<List<Series>>>(Resource.Loading())
    val seriesPopularState: StateFlow<Resource<List<Series>>> = _seriesPopularState

    init {
        fetchMoviesPopular()
        fetchMoviesUpcoming()
        fetchSeriesPopular()
    }

    private fun fetchMoviesPopular() {
        viewModelScope.launch {
            movieRepository.getMoviesPopular(forceFetchFromRemote = false, page = 1)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    _moviesPopular.value = result
                }
        }
    }

    private fun fetchMoviesUpcoming() {
        viewModelScope.launch {
            movieRepository.getMoviesUpcoming(forceFetchFromRemote = false, page = 1)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    _moviesUpcoming.value = result
                }
        }
    }


    private fun fetchSeriesPopular() {
        viewModelScope.launch {
            seriesRepository.getSeriesPopular(forceFetchFromRemote = false, page = 1)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    _seriesPopularState.value = result
                }
        }
    }

}