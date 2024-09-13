package com.example.alexstarter.data.remote.series

import com.example.alexstarter.data.remote.common.GenericListDto
import com.example.alexstarter.data.remote.series.dto.SerieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieApi {

    @GET("tv/popular")
    suspend fun getSeriesPopular(
        @Query("page") page: Int
    ): GenericListDto<SerieDto>

    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(
        @Path("series_id") seriesId: String
    ): SerieDto

}