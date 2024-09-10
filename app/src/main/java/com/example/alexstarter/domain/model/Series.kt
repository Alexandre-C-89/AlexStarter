package com.example.alexstarter.domain.model

import com.google.gson.annotations.SerializedName

data class Series(
    val id: Int,
    @SerializedName("original_name") val title: String,
    val description: String,
    val image: String,
)
