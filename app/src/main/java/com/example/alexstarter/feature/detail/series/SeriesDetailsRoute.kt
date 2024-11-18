package com.example.alexstarter.feature.detail.series

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.appbar.TopBar
import com.example.alexstarter.designsystem.message.ErrorMessage
import com.example.alexstarter.designsystem.text.Text
import com.example.alexstarter.designsystem.text.Title
import com.example.alexstarter.designsystem.text.TitleWithRow
import com.example.alexstarter.ui.theme.DarkBlue

@Composable
fun SeriesDetailRoute(
    seriesId: String,
    viewModel: SeriesDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val seriesDetailsState by viewModel.seriesDetailsState.collectAsStateWithLifecycle()
    SeriesDetailScreen(
        viewModel = viewModel,
        seriesDetailsState = seriesDetailsState,
        onBackClick = onBackClick
    )

    LaunchedEffect(seriesId) {
        viewModel.fetchSeriesDetails(seriesId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesDetailScreen(
    seriesDetailsState: SeriesDetailsState,
    onBackClick: () -> Unit,
    viewModel: SeriesDetailsViewModel
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
            when (seriesDetailsState) {
                is SeriesDetailsState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorMessage(text = "Oh no something went wrong !")
                    }
                }

                is SeriesDetailsState.Loaded -> {

                    val seriesPopularity = seriesDetailsState.series.popularity
                    val progress = viewModel.convertRatingToProgress(seriesPopularity)
                    val series = seriesDetailsState.series

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomStart
                    ) {
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
                            model = series.posterPath,
                            contentScale = ContentScale.Crop,
                            contentDescription = "image of ${series.name}"
                        )
                        TitleWithRow(text = series.name)
                    }
                    Column(modifier = Modifier.padding(8.dp)) {

                        Spacer.Vertical.Default()
                        Text.Default(text = "Description :")
                        Spacer.Vertical.Small()
                        Text.Default(text = series.overview)
                        Spacer.Vertical.Default()
                        series.type?.let { Text.Default(text = "Type : ${series.type}") }
                        Spacer.Vertical.Default()
                        series.status?.let { Text.Default(text = "Status : ${series.status}") }
                        Spacer.Vertical.Default()
                        Text.Default(text = "first air date : ${series.firstAirDate}")
                        Spacer.Vertical.Default()
                        series.homepage?.let { Text.Default(text = "Homepage : $it") }
                        Spacer.Vertical.Default()
                        Text.Default(text = "Originale language : ${series.originalLanguage}")
                        Spacer.Vertical.Default()
                        series.tagline?.let { Text.Default(text = "Tagline : ${series.tagline}") }
                    }
                }
                SeriesDetailsState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}