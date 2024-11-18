package com.example.alexstarter.data.repository.di

import com.example.alexstarter.data.locale.AppDatabase
import com.example.alexstarter.data.remote.actor.ActorApi
import com.example.alexstarter.data.remote.movie.MovieApi
import com.example.alexstarter.data.remote.series.SerieApi
import com.example.alexstarter.data.repository.actor.ActorRepositoryImpl
import com.example.alexstarter.data.repository.movie.MovieRepositoryImpl
import com.example.alexstarter.data.repository.series.SeriesRepositoryImpl
import com.example.alexstarter.domain.actor.repository.ActorRepository
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
        serieApi: SerieApi,
        appDatabase: AppDatabase
    ): SeriesRepository {
        return SeriesRepositoryImpl(serieApi,appDatabase)
    }

    @Provides
    @Singleton
    fun providesActorRepository(
        actorApi: ActorApi
    ): ActorRepository {
        return ActorRepositoryImpl(actorApi)
    }

}