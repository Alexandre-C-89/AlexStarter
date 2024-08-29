package com.example.alexstarter.domain.repository

import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMoviesPopular(
        forceFetchFromRemote: Boolean,
        //category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMoviesUpcoming(
        forceFetchFromRemote: Boolean,
        //category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}