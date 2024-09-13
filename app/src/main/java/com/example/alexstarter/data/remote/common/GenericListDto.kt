package com.example.alexstarter.data.remote.common

import com.google.gson.annotations.SerializedName

data class GenericListDto<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results")val totalResults: Int
)