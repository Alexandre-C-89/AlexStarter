package com.example.alexstarter.domain.repository

import com.example.alexstarter.domain.model.CastMember
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.domain.model.MovieCredits
import com.example.alexstarter.domain.model.Series
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMoviesPopular(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMoviesUpcoming(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getSeriesPopular(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Series>>>

    suspend fun getMovieDetails(movieId: String): Flow<Resource<Movie>>

    suspend fun getMovieCredits(movieId: String): Flow<Resource<List<CastMember>>>

    suspend fun getSeriesDetails(seriesId: String): Flow<Resource<Series>>

}