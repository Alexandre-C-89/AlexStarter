package com.example.alexstarter.data.locale.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    /*@Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity*/

    @Query("SELECT * FROM movie")
    suspend fun getMoviesPopular(): List<MovieEntity>

    @Query("SELECT * FROM movie")
    suspend fun getMoviesUpcoming(): List<MovieEntity>

}