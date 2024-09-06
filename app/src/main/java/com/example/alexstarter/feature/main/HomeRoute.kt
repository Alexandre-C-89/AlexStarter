package com.example.alexstarter.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.MovieItem
import com.example.alexstarter.designsystem.SeriesItem
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.TopBar
import com.example.alexstarter.designsystem.tab.TabRowApp
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.domain.model.Series
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily
import com.example.alexstarter.util.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val moviesPopularState by viewModel.moviesPopular.collectAsStateWithLifecycle()
    val moviesUpcomingState by viewModel.moviesUpcoming.collectAsStateWithLifecycle()
    val seriesPopularState by viewModel.seriesPopularState.collectAsStateWithLifecycle()
    HomeScreen(
        moviesPopularState = moviesPopularState,
        moviesUpcomingState = moviesUpcomingState,
        seriesPopularState = seriesPopularState,
        onMovieClick = { movieId ->
            navController.navigate("movie/$movieId")
        },
        onSeriesClick = { seriesId ->
            navController.navigate("movie/$seriesId")
        },
        onMenuClick = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    moviesPopularState: Resource<List<Movie>>,
    moviesUpcomingState: Resource<List<Movie>>,
    seriesPopularState: Resource<List<Series>>,
    onMovieClick: (Int) -> Unit,
    onSeriesClick: (Int) -> Unit,
    onMenuClick: () -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onMenuClick,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            TabRowApp(modifier = Modifier.weight(1f))
            Spacer.Vertical.Small()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Popular",
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    color = DarkBlue
                )

                Spacer.Vertical.Small()

                when (moviesPopularState) {
                    is Resource.Error -> {}
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

                        Spacer.Vertical.Medium()

                        Text(
                            text = "Upcoming",
                            fontFamily = openSansFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = DarkBlue
                        )

                        Spacer.Vertical.Small()

                        when (moviesUpcomingState) {
                            is Resource.Error -> { /* Gérer l'erreur */
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

                        Text(
                            text = "Series popular",
                            fontFamily = openSansFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = DarkBlue
                        )

                        Spacer.Vertical.Default()

                        when (seriesPopularState) {
                            is Resource.Error -> { /* Gérer l'erreur */
                            }

                            is Resource.Loading -> {
                                CircularProgressIndicator()
                            }

                            is Resource.Success -> {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(seriesPopularState.data!!.size) { index ->
                                        SeriesItem(
                                            onClick = { onSeriesClick(seriesPopularState.data[index].id) },
                                            series = seriesPopularState.data[index]
                                        )
                                    }
                                }

                            }
                        }

                    }

                }

                Spacer.Vertical.Medium()

            }
        }

    }
}