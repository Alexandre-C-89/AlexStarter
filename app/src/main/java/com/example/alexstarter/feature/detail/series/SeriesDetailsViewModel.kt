package com.example.alexstarter.feature.detail.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alexstarter.domain.movie.repository.MovieRepository
import com.example.alexstarter.domain.series.repository.SeriesRepository
import com.example.alexstarter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val repository: SeriesRepository
) : ViewModel() {

    private val _seriesDetailsState =
        MutableStateFlow<SeriesDetailsState>(SeriesDetailsState.Loading)
    val seriesDetailsState: StateFlow<SeriesDetailsState> = _seriesDetailsState

    fun fetchSeriesDetails(seriesId: String) {
        viewModelScope.launch {
            repository.getSeriesDetails(seriesId).collect { resource ->
                _seriesDetailsState.value = when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            SeriesDetailsState.Loaded(it)
                        } ?: SeriesDetailsState.Error("Series not found")
                    }
                    is Resource.Error -> SeriesDetailsState.Error(resource.message ?: "Error loading series details")
                    is Resource.Loading -> SeriesDetailsState.Loading
                }
            }
        }
    }
}