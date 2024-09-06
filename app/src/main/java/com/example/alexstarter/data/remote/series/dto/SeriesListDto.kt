package com.example.alexstarter.data.remote.series.dto

import com.google.gson.annotations.SerializedName

data class SeriesListDto(
    val page: Int,
    val results: List<SeriesDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results")val totalResults: Int
)
