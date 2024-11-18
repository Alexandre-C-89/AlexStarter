package com.example.alexstarter.feature.detail.movie

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.alexstarter.R
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.appbar.TopBar
import com.example.alexstarter.designsystem.image.ImageCardItem
import com.example.alexstarter.designsystem.indicator.CircularIndicator
import com.example.alexstarter.designsystem.message.ErrorMessage
import com.example.alexstarter.designsystem.text.Text
import com.example.alexstarter.designsystem.text.TextWithThumbnail
import com.example.alexstarter.designsystem.text.Title
import com.example.alexstarter.designsystem.text.TitleWithRow
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun MovieDetailRoute(
    navController: NavController,
    movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    //onActorClick: (Int) -> Unit
) {
    val movieDetailsState by viewModel.movieDetailsState.collectAsStateWithLifecycle()
    MovieDetailScreen(
        viewModel = viewModel,
        movieDetailsState = movieDetailsState,
        onBackClick = onBackClick,
        onActorClick = { actorId ->
            navController.navigate("actor/$actorId") }
    )

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    movieDetailsState: MovieDetailState,
    onBackClick: () -> Unit,
    onActorClick: (Int) -> Unit,
) {
    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = DarkBlue)
        ) {
            when (movieDetailsState) {


                is MovieDetailState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorMessage(text = "Oh no something went wrong !")
                    }
                }

                is MovieDetailState.Loaded -> {

                    //val movieRating = movieDetailsState.movie.moyenneDesVotes
                    //val progress = viewModel.convertRatingToProgress(movieRating)
                    val movie = movieDetailsState.movie

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Log.d("MOVIEDETAILSROUTE", "${movie.image}")
                        AsyncImage(
                            modifier = Modifier
                                .height(350.dp)
                                .graphicsLayer { alpha = 0.99f }
                                .drawWithContent {
                                    val colors = listOf(
                                        Color.Black,
                                        Color.Transparent
                                    )
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(colors),
                                        blendMode = BlendMode.DstIn
                                    )
                                },
                            model = movie.image,
                            contentScale = ContentScale.Crop,
                            contentDescription = "image of ${movie.title}"
                        )
                        TitleWithRow(text = movie.title)
                    }
                    Column(modifier = Modifier.padding(8.dp)) {

                        Spacer.Vertical.Default()
                        Text.Small(text = movie.overview)
                        Spacer.Vertical.Default()
                        Text.Default(text = "Date de sortie : ${movie.dateDeSortie}")
                        Spacer.Vertical.Default()
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(movie.genres) { genre ->
                                TextWithThumbnail(text = genre)
                            }
                        }
                        Spacer.Vertical.Default()
                        Text.Default(text = "Status : ${movie.status}")
                        Spacer.Vertical.Default()
                        Text.Default(text = "Actors :")
                        Spacer.Vertical.Default()
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(movie.cast) { castMember ->
                                if (castMember.profilePath?.isNotEmpty() == true) {
                                    ImageCardItem(
                                        image = castMember.profilePath,
                                        text = castMember.name,
                                        onClick = { onActorClick(castMember.id) }
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