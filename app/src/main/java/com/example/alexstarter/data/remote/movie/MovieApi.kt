package com.example.alexstarter.data.remote.movie

import com.example.alexstarter.data.remote.common.GenericListDto
import com.example.alexstarter.data.remote.movie.dto.MovieDto
import com.example.alexstarter.domain.movie.model.MovieCredits
import com.example.alexstarter.domain.movie.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("page") page: Int
    ): GenericListDto<MovieDto>

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("page") page: Int
    ): GenericListDto<MovieDto>

    @GET("trending/movie/{time_window}")
    suspend fun getTendencyMoviesPopular(
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int
    ): GenericListDto<MovieDto>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String
    ): MovieDto

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideos(
        @Path("movieId") movieId: String
    ): VideoResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String
    ): MovieCredits

}