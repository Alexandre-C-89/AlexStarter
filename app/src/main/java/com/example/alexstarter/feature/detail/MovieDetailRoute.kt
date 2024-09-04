package com.example.alexstarter.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.TopBar

@Composable
fun MovieDetailRoute(
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel()
){
    val movieDetailsState by viewModel.movieDetailsState.collectAsStateWithLifecycle()
    MovieDetailScreen(
        movieDetailsState = movieDetailsState,
    )

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailsState: MovieDetailState
){
    AppScaffold(
        topBar = {
            TopBar()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when (movieDetailsState){
                is MovieDetailState.Error -> {
                    Text(text = movieDetailsState.message)
                }
                
                is MovieDetailState.Loaded -> {
                    val movie = movieDetailsState.movie
                    Text(text = movie.title)
                }
                
                MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}