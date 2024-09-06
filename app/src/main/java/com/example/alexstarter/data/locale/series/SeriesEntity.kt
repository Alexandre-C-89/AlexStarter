package com.example.alexstarter.data.locale.series

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SeriesEntity (
    @PrimaryKey
    val id: Int,
    //val title: String,
    val image: String?,
    val overview: String?
)