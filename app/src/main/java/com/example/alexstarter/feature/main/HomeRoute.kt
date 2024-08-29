package com.example.alexstarter.feature.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.MovieItem
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.TopBar
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily
import com.example.alexstarter.util.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val moviesPopularState by viewModel.moviesPopular.collectAsStateWithLifecycle()
    val moviesUpcomingState by viewModel.moviesUpcoming.collectAsStateWithLifecycle()
    HomeScreen(
        moviesPopularstate = moviesPopularState,
        moviesUpcomingState = moviesUpcomingState,
        onMenuClick = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    moviesPopularstate: Resource<List<Movie>>,
    moviesUpcomingState: Resource<List<Movie>>,
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
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp)
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

            when (moviesPopularstate) {
                is Resource.Error -> {}
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ){

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(moviesPopularstate.data!!.size) { index ->
                                MovieItem(
                                    movie = moviesPopularstate.data[index]
                                )
                            }
                        }

                        Spacer.Vertical.Default()

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
                            is Resource.Error -> { /* Gérer l'erreur */ }
                            is Resource.Loading -> {
                                CircularProgressIndicator()
                            }
                            is Resource.Success -> {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(moviesUpcomingState.data!!.size) { index ->
                                        MovieItem(movie = moviesUpcomingState.data[index])
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}