package com.example.alexstarter.data.repository.di

import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.remote.MovieApi
import com.example.alexstarter.data.remote.series.SeriesApi
import com.example.alexstarter.data.repository.MovieRepositoryImpl
import com.example.alexstarter.domain.repository.MovieRepository
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
        seriesApi: SeriesApi,
        appDatabase: AppDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi, seriesApi,appDatabase)
    }
}