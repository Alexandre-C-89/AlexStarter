package com.example.alexstarter.data.repository.di

import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.remote.MovieApi
import com.example.alexstarter.data.remote.series.SeriesApi
import com.example.alexstarter.data.repository.movie.MovieRepositoryImpl
import com.example.alexstarter.data.repository.series.SeriesRepositoryImpl
import com.example.alexstarter.domain.movie.repository.MovieRepository
import com.example.alexstarter.domain.series.repository.SeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        movieApi: MovieApi,
        appDatabase: AppDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi,appDatabase)
    }

    @Provides
    @Singleton
    fun providesSeriesRepository(
        seriesApi: SeriesApi,
        appDatabase: AppDatabase
    ): SeriesRepository {
        return SeriesRepositoryImpl(seriesApi,appDatabase)
    }

}