package com.example.alexstarter.data.repository

import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.locale.movie.toMovie
import com.example.alexstarter.data.locale.movie.toMovieEntity
import com.example.alexstarter.data.remote.MovieApi
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.domain.repository.MovieRepository
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
        //category: String,
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
        //category: String,
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

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {

            emit(Resource.Loading())

            val movieEntity = appDatabase.movieDao.getMovieById(id)

            if (movieEntity != null) {
                emit(
                    Resource.Success(movieEntity.toMovie())
                )
                return@flow
            }
            //emit(Resource.Error("Error no such movie"))
        }
    }

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        try {
            val movie = movieApi.getMovieDetails(movieId)
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}