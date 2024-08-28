package com.example.alexstarter.data.repository

import android.util.Log
import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.locale.movie.toMovie
import com.example.alexstarter.data.locale.movie.toMovieEntity
import com.example.alexstarter.data.remote.MovieApi
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.domain.repository.MovieListRepository
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val appDatabase: AppDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading())

            val localMovieList = appDatabase.movieDao.getMovieListByCategory()

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie()
                    }
                ))

                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMoviesList(category, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    Log.d("MOVIELISTREPOSITORYIMPL", movieDto.poster_path)
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
            emit(Resource.Error("Error no such movie"))
        }
    }
}