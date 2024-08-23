package com.example.alexstarter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    var isLoading = mutableStateOf(false)

    private var _getMoviesData: MutableLiveData<List<MovieResponse>> = MutableLiveData()
    var getMoviesData: LiveData<List<MovieResponse>> = _getMoviesData

    suspend fun getMoviesData(): Resource<List<MovieResponse>> {
        val result = movieRepository.getMovieResponse()
        if (result is Resource.Success) {
            isLoading.value = true
            _getMoviesData.value = result.data!!
        }

        return result

    }
}