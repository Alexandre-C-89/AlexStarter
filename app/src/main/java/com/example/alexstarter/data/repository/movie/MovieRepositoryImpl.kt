package com.example.alexstarter.data.repository.movie

import android.util.Log
import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.repository.movie.mapper.toCastMembers
import com.example.alexstarter.data.repository.movie.mapper.toDomain
import com.example.alexstarter.data.repository.movie.mapper.toEntity
import com.example.alexstarter.data.remote.movie.MovieApi
import com.example.alexstarter.domain.movie.model.CastMember
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.domain.movie.repository.MovieRepository
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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
                        movieEntity.toDomain()
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
                    movieDto.toEntity()
                }
            }

            appDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toDomain() }
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
                        movieEntity.toDomain()
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
                    movieDto.toEntity()
                }
            }

            appDatabase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toDomain() }
            ))

        }
    }

    override suspend fun getMovieDetails(movieId: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        try {
            val movieDto = movieApi.getMovieDetails(movieId)
            val movie = movieDto.toEntity().toDomain()  // Mapping
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

    override suspend fun getMovieVideos(movieId: String): Flow<String?> = flow {
        val response = movieApi.getMovieVideos(movieId)
        val videoKey = response.results
            .firstOrNull { it.site == "YouTube" && it.type == "Trailer" && it.official }
            ?.key

        emit(videoKey) // Émet la clé de la première vidéo trouvée ou null
    }.catch { e ->
        emit(null) // Émet null en cas d'erreur
    }

}