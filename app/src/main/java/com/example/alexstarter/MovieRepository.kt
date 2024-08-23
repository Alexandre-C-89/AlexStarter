package com.example.alexstarter

import com.example.alexstarter.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getMovieResponse(): Resource<List<MovieResponse>> {
        val response = try {
            apiInterface.getMovies()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}