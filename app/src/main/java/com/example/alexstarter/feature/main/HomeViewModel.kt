package com.example.alexstarter.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.domain.repository.MovieListRepository
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieListRepository
): ViewModel() {

    private val _movies = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val movies: StateFlow<Resource<List<Movie>>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            repository.getMovieList(forceFetchFromRemote = false, category = "popular", page = 1)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    Log.d("AZZERT", result.toString())
                    _movies.value = result
                }
        }
    }


}