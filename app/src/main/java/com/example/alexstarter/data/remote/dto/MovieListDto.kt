package com.example.alexstarter.data.remote.dto

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import retrofit2.http.QueryName

data class MovieListDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results")val totalResults: Int
)
