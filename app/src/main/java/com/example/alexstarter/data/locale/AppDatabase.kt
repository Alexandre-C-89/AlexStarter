package com.example.alexstarter.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alexstarter.data.locale.movie.MovieDao
import com.example.alexstarter.data.locale.movie.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao
}