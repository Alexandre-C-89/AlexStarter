package com.example.alexstarter.feature.detail.series

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data != null) {
                            Log.d("SeriesDetailsViewModel", "Series loaded successfully")
                            _seriesDetailsState.value = SeriesDetailsState.Loaded(resource.data)
                        } else {
                            Log.e("SeriesDetailsViewModel", "Series data is null")
                            _seriesDetailsState.value = SeriesDetailsState.Error("Series not found")
                        }
                    }
                    is Resource.Error -> {
                        Log.e("SeriesDetailsViewModel", "Error: ${resource.message}")
                        _seriesDetailsState.value =
                            SeriesDetailsState.Error(resource.message ?: "Error loading series details")
                    }
                    is Resource.Loading -> {
                        Log.d("SeriesDetailsViewModel", "Loading series details")
                        _seriesDetailsState.value = SeriesDetailsState.Loading
                    }
                }
            }
        }
    }

    fun convertRatingToProgress(rating: Double): Float {
        return (rating / 10).toFloat()
    }

}