package com.example.alexstarter.data.repository.series

import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.remote.series.SerieApi
import com.example.alexstarter.domain.series.model.Series
import com.example.alexstarter.domain.series.repository.SeriesRepository
import com.example.alexstarter.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor (
    private val serieApi: SerieApi,
    private val appDatabase: AppDatabase
) : SeriesRepository {

    override suspend fun getSeriesPopular(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Series>>> {
        return flow {

            emit(Resource.Loading())
            val localSeriesPopular = appDatabase.seriesDao.getSeriesPopular()
            val shouldLoadLocalSeries = localSeriesPopular.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalSeries) {
                emit(Resource.Success(
                    data = localSeriesPopular.map { seriesEntity ->
                        seriesEntity.toSeries()
                    }
                ))
                return@flow
            }

            val seriesPopularFromApi = try {
                serieApi.getSeriesPopular(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading series popular"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading series popular"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading series popular"))
                return@flow
            }

            val seriesEntities = seriesPopularFromApi.results.let {
                it.map { seriesDto ->
                    seriesDto.toSeriesEntity()
                }
            }

            appDatabase.seriesDao.upsertSeriesList(seriesEntities)

            emit(Resource.Success(
                seriesEntities.map { it.toSeries() }
            ))

        }
    }

    override suspend fun getSeriesTopRated(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Series>>> {
        return flow {

            emit(Resource.Loading())
            val localSeriesTopRated = appDatabase.seriesDao.getSeriesTopRated()
            val shouldLoadLocalSeries = localSeriesTopRated.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalSeries) {
                emit(Resource.Success(
                    data = localSeriesTopRated.map { seriesEntity ->
                        seriesEntity.toSeries()
                    }
                ))
                return@flow
            }

            val seriesTopRatedFromApi = try {
                serieApi.getSeriesTopRated(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading series popular"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading series popular"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading series popular"))
                return@flow
            }

            val seriesEntities = seriesTopRatedFromApi.results.let {
                it.map { seriesDto ->
                    seriesDto.toSeriesEntity()
                }
            }

            appDatabase.seriesDao.upsertSeriesList(seriesEntities)

            emit(Resource.Success(
                seriesEntities.map { it.toSeries() }
            ))

        }
    }

    override suspend fun getSeriesDetails(seriesId: String): Flow<Resource<Series>> = flow {
        emit(Resource.Loading())
        try {
            val seriesDto = serieApi.getSeriesDetails(seriesId)
            val series = seriesDto.toSeriesEntity().toSeries()  // Mapping
            emit(Resource.Success(series))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }


}