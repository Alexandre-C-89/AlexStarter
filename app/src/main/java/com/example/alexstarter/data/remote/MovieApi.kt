package com.example.alexstarter.data.remote

import com.example.alexstarter.data.remote.di.RemoteModule
import com.example.alexstarter.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = RemoteModule.API_KEY
    ): MovieListDto

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = RemoteModule.API_KEY
    ): MovieListDto

}