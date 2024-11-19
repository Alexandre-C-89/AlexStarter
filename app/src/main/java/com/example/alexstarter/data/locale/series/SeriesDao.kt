package com.example.alexstarter.data.locale.series

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SeriesDao {

    @Upsert
    suspend fun upsertSeriesList(seriesList: List<SeriesEntity>)

    @Query("SELECT * FROM series")
    suspend fun getSeriesPopular(): List<SeriesEntity>

    @Query("SELECT * FROM series")
    suspend fun getSeriesTopRated(): List<SeriesEntity>

}