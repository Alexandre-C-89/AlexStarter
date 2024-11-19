package com.example.alexstarter.domain.series.repository

import com.example.alexstarter.domain.series.model.Series
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getSeriesPopular(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Series>>>

    suspend fun getSeriesTopRated(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Series>>>

    suspend fun getSeriesDetails(seriesId: String): Flow<Resource<Series>>

}