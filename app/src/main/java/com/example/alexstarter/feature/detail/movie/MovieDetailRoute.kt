package com.example.alexstarter.feature.detail.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.alexstarter.R
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.TopBar
import com.example.alexstarter.designsystem.image.ImageCardItem
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun MovieDetailRoute(
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val movieDetailsState by viewModel.movieDetailsState.collectAsStateWithLifecycle()
    MovieDetailScreen(
        movieDetailsState = movieDetailsState,
        onBackClick = onBackClick
    )

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailsState: MovieDetailState,
    onBackClick: () -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                    Column(modifier = Modifier.padding(8.dp)) {

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
                            text = "Genres : ${movie.genres.joinToString(", ")}",
                            fontFamily = openSansFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = DarkBlue
                        )
                        Spacer.Vertical.Small()
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(movie.cast) { castMember ->
                                if (castMember.profilePath?.isNotEmpty() == true) {
                                    ImageCardItem(
                                        image = castMember.profilePath
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_no_image),
                                        contentDescription = "Placeholder for ${castMember.name}",
                                        modifier = Modifier
                                            .height(50.dp)
                                            .width(50.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }

                    }

                }

                MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}