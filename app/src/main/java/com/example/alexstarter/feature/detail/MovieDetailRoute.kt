package com.example.alexstarter.feature.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.TopBar
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun MovieDetailRoute(
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val movieDetailsState by viewModel.movieDetailsState.collectAsStateWithLifecycle()
    MovieDetailScreen(
        movieDetailsState = movieDetailsState,
    )

    LaunchedEffect(movieId) {
        Log.d("MovieDetailRoute", "Fetching details for movieId: $movieId")
        viewModel.fetchMovieDetails(movieId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailsState: MovieDetailState
) {
    AppScaffold(
        topBar = {
            TopBar()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when (movieDetailsState) {
                is MovieDetailState.Error -> {
                    Text(text = movieDetailsState.message)
                }

                is MovieDetailState.Loaded -> {
                    val movie = movieDetailsState.movie
                    AsyncImage(
                        modifier = Modifier.height(350.dp),
                        model = movie.image,
                        contentScale = ContentScale.Crop,
                        contentDescription = "image of ${movie.title}"
                    )
                    Column(modifier = Modifier.padding(8.dp)){
                        Text(
                            text = movie.title,
                            fontSize = 14.sp,
                            fontFamily = openSansFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = DarkBlue
                        )
                        Spacer.Vertical.Default()
                        Text(
                            text = movie.overview,
                            fontFamily = openSansFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            color = DarkBlue
                        )
                    }
                    Spacer.Vertical.Small()
                    Text(
                        text = "Date de sortie : ${movie.dateDeSortie}",
                        fontFamily = openSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkBlue
                    )
                    Spacer.Vertical.Small()
                    Text(
                        text = "Type de film : ${movie.dateDeSortie}",
                        fontFamily = openSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkBlue
                    )
                    Spacer.Vertical.Small()
                    Text(
                        text = "Genres : ${movie.genres.joinToString(", ")}",
                        fontFamily = openSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkBlue
                    )
                    Spacer.Vertical.Small()
                    /*Row(modifier = Modifier.fillMaxSize()){
                        Image(
                            painter = , contentDescription = "image of actors"
                        )
                    }*/
                }

                MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}