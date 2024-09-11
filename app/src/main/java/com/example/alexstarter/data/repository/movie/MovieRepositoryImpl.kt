package com.example.alexstarter.data.repository.movie

import android.util.Log
import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.locale.movie.toCastMembers
import com.example.alexstarter.data.locale.movie.toMovie
import com.example.alexstarter.data.locale.movie.toMovieEntity
import com.example.alexstarter.data.remote.MovieApi
import com.example.alexstarter.domain.movie.model.CastMember
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.domain.movie.repository.MovieRepository
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val appDatabase: AppDatabase
) : MovieRepository {

    override suspend fun getMoviesPopular(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading())

            val localMoviePopular = appDatabase.movieDao.getMoviesPopular()

            val shouldLoadLocalMovie = localMoviePopular.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMoviePopular.map { movieEntity ->
                        movieEntity.toMovie()
                    }
                ))

                return@flow
            }

            val moviesPopularFromApi = try {
                movieApi.getMoviesPopular(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies popular"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies popular"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies popular"))
                return@flow
            }

            val movieEntities = moviesPopularFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }

            appDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toMovie() }
            ))

        }
    }

    override suspend fun getMoviesUpcoming(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading())

            val localMovieUpcoming = appDatabase.movieDao.getMoviesUpcoming()

            val shouldLoadLocalMoviesUpcoming =
                localMovieUpcoming.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMoviesUpcoming) {
                emit(Resource.Success(
                    data = localMovieUpcoming.map { movieEntity ->
                        movieEntity.toMovie()
                    }
                ))

                return@flow
            }

            val moviesUpcomingFromApi = try {
                movieApi.getMoviesUpcoming(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies upcoming"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies upcoming"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies upcoming"))
                return@flow
            }

            val movieEntities = moviesUpcomingFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }

            appDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toMovie() }
            ))

        }
    }

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        try {
            val movieDto = movieApi.getMovieDetails(movieId)
            val movie = movieDto.toMovieEntity().toMovie()  // Mapping
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }

    override suspend fun getMovieCredits(movieId: String): Flow<Resource<List<CastMember>>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieApi.getMovieCredits(movieId)

            // Mappage des URLs des images des acteurs
            val castMembers = response.toCastMembers()
            Log.d("GETMOVIECREDITS", "$response + $castMembers")

            emit(Resource.Success(castMembers))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

}