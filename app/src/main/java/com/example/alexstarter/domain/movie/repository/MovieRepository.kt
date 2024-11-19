package com.example.alexstarter.domain.movie.repository

import com.example.alexstarter.domain.movie.model.CastMember
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.domain.movie.model.VideoResponse
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

    suspend fun getMovieDetails(movieId: String): Flow<Resource<Movie>>

    suspend fun getMovieCredits(movieId: String): Flow<Resource<List<CastMember>>>

    suspend fun getMovieVideos(movieId: String): Flow<String?>

}