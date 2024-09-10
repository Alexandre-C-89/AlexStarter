package com.example.alexstarter.feature.detail.series

import com.example.alexstarter.domain.model.Series

sealed class SeriesDetailsState {

    data class Error(val message: String) : SeriesDetailsState()

    object Loading : SeriesDetailsState()

    data class Loaded(val series: Series) : SeriesDetailsState()

}