package com.example.alexstarter.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alexstarter.data.locale.actor.ActorDao
import com.example.alexstarter.data.locale.actor.ActorEntity
import com.example.alexstarter.data.locale.movie.MovieDao
import com.example.alexstarter.data.locale.movie.MovieEntity
import com.example.alexstarter.data.locale.series.SeriesDao
import com.example.alexstarter.data.locale.series.SeriesEntity

@Database(
    entities = [MovieEntity::class, SeriesEntity::class, ActorEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val seriesDao: SeriesDao
    abstract val actorDao: ActorDao
}