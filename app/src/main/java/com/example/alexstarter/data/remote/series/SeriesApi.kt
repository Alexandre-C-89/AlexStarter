package com.example.alexstarter.data.remote.series

import com.example.alexstarter.data.remote.di.RemoteModule
import com.example.alexstarter.data.remote.dto.MovieDto
import com.example.alexstarter.data.remote.series.dto.SeriesDto
import com.example.alexstarter.data.remote.series.dto.SeriesListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("tv/popular")
    suspend fun getSeriesPopular(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = RemoteModule.API_KEY
    ): SeriesListDto

    @GET("tv/{series_id}")
    suspend fun getSeriesDetails(
        @Path("series_id") seriesId: String,
        @Query("api_key") apiKey: String = RemoteModule.API_KEY
    ): SeriesDto

}