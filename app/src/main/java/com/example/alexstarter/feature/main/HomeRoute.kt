package com.example.alexstarter.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.MovieItem
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.card.CardSeriesItem
import com.example.alexstarter.designsystem.message.ErrorMessage
import com.example.alexstarter.designsystem.text.Title
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.domain.series.model.Series
import com.example.alexstarter.util.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    //onNavigateClick: (String) -> Unit
) {
    val moviesNowPlayingState by viewModel.moviesNowPlaying.collectAsStateWithLifecycle()
    val moviesPopularState by viewModel.moviesPopular.collectAsStateWithLifecycle()
    val moviesUpcomingState by viewModel.moviesUpcoming.collectAsStateWithLifecycle()
    val seriesPopularState by viewModel.seriesPopularState.collectAsStateWithLifecycle()
    val seriesTopRatedState by viewModel.seriesTopRatedState.collectAsStateWithLifecycle()
    HomeScreen(
        moviesNowPlayingState = moviesNowPlayingState,
        moviesPopularState = moviesPopularState,
        moviesUpcomingState = moviesUpcomingState,
        seriesPopularState = seriesPopularState,
        seriesTopRatedState = seriesTopRatedState,
        onMovieClick = { movieId ->
            navController.navigate("movie/$movieId")
        },
        onSeriesClick = { seriesId ->
            navController.navigate("tv/$seriesId")
        },
        onMenuClick = {},
        //onNavigateClick = onNavigateClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    moviesNowPlayingState: Resource<List<Movie>>,
    moviesPopularState: Resource<List<Movie>>,
    moviesUpcomingState: Resource<List<Movie>>,
    seriesPopularState: Resource<List<Series>>,
    seriesTopRatedState: Resource<List<Series>>,
    onMovieClick: (Int) -> Unit,
    onSeriesClick: (Int) -> Unit,
    onMenuClick: () -> Unit
) {

    AppScaffold{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Title.Big(text = "Now playing")

            Spacer.Vertical.Default()

            when(moviesNowPlayingState){

                is Resource.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorMessage(text = "Oh no something went wrong !")
                    }
                }

                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(moviesNowPlayingState.data!!.size) { index ->
                            MovieItem(
                                onClick = { onMovieClick(moviesNowPlayingState.data[index].id) },
                                movie = moviesNowPlayingState.data[index]
                            )
                        }
                    }
                }

            }

            Spacer.Vertical.Default()

            Title.Big(text = "Popular")

            Spacer.Vertical.Default()

            when (moviesPopularState) {
                is Resource.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorMessage(text = "Oh no something went wrong !")
                    }
                }

                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(moviesPopularState.data!!.size) { index ->
                            MovieItem(
                                onClick = { onMovieClick(moviesPopularState.data[index].id) },
                                movie = moviesPopularState.data[index]
                            )
                        }
                    }
                }
            }

            Spacer.Vertical.Medium()

            Title.Big(text = "Upcoming")

            Spacer.Vertical.Default()

            when (moviesUpcomingState) {
                is Resource.Error -> {
                    ErrorMessage(text = "Oh no something went wrong !")
                }

                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(moviesUpcomingState.data!!.size) { index ->
                            MovieItem(
                                onClick = { onMovieClick(moviesUpcomingState.data[index].id) },
                                movie = moviesUpcomingState.data[index]
                            )
                        }
                    }

                }
            }

            Spacer.Vertical.Default()

            Title.Big(text = "Series popular")

            Spacer.Vertical.Default()

            when (seriesPopularState) {
                is Resource.Error -> {
                    ErrorMessage(text = "Oh no something went wrong !")
                }

                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(seriesPopularState.data!!.size) { index ->
                            CardSeriesItem(
                                onClick = { onSeriesClick(seriesPopularState.data[index].id) },
                                series = seriesPopularState.data[index]
                            )
                        }
                    }

                }
            }

            Spacer.Vertical.Default()

            Title.Big(text = "Series Top Rated")

            Spacer.Vertical.Default()

            when (seriesTopRatedState) {
                is Resource.Error -> {
                    ErrorMessage(text = "Oh no something went wrong !")
                }

                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(seriesTopRatedState.data!!.size) { index ->
                            CardSeriesItem(
                                onClick = { onSeriesClick(seriesTopRatedState.data[index].id) },
                                series = seriesTopRatedState.data[index]
                            )
                        }
                    }

                }
            }

        }

    }
}