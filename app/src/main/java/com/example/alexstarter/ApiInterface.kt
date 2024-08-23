package com.example.alexstarter

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): List<MovieResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "ec4c99f9d011ee3e90ae246123040c86"
    }

}