package com.example.alexstarter.domain.model

import com.google.gson.annotations.SerializedName

data class CastMember(
    val id: Int,
    val name: String,
    val character: String,
    @SerializedName("profile_path") val profilePath: String?
)
