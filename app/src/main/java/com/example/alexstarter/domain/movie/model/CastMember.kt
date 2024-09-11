package com.example.alexstarter.domain.movie.model

import com.google.gson.annotations.SerializedName

data class CastMember(
    val id: Int,
    val name: String,
    val character: String,
    @SerializedName("profile_path") val profilePath: String?
)
