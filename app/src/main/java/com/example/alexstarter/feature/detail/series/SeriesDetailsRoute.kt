package com.example.alexstarter.feature.detail.series

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.alexstarter.designsystem.text.TitleWithRow

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
                onNavigationClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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

                    AsyncImage(
                        modifier = Modifier.height(350.dp),
                        model = series.posterPath,
                        contentScale = ContentScale.Crop,
                        contentDescription = "image of ${series.name}"
                    )
                    Column(modifier = Modifier.padding(8.dp)) {

                        TitleWithRow(text = series.name, progress = progress)
                        Spacer.Vertical.Default()
                        Text.Default(text = series.overview)
                        Spacer.Vertical.Small()
                        series.type?.let { Text.Default(text = series.type) }
                        Spacer.Vertical.Small()
                        series.status?.let { Text.Default(text = series.status) }
                        Spacer.Vertical.Small()
                        Text.Default(text = series.firstAirDate)
                        Spacer.Vertical.Small()
                        series.homepage?.let { Text.Default(text = it) }
                        Spacer.Vertical.Small()
                        Text.Default(text = series.originalLanguage)
                        Spacer.Vertical.Small()
                        series.tagline?.let { Text.Default(text = series.tagline) }
                    }
                }
                SeriesDetailsState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}