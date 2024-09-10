package com.example.alexstarter.feature.detail.series

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.TopBar
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun SeriesDetailRoute(
    seriesId: String,
    viewModel: SeriesDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val seriesDetailsState by viewModel.seriesDetailsState.collectAsStateWithLifecycle()
    SeriesDetailScreen(
        seriesDetailsState = seriesDetailsState,
        onBackClick = onBackClick
    )

    LaunchedEffect(seriesId) {
        Log.d("LAUNCHED", seriesId)
        viewModel.fetchSeriesDetails(seriesId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesDetailScreen(
    seriesDetailsState: SeriesDetailsState,
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
            when (seriesDetailsState) {
                is SeriesDetailsState.Error -> {
                    Text(text = seriesDetailsState.message)
                }

                is SeriesDetailsState.Loaded -> {

                    val series = seriesDetailsState.series

                    AsyncImage(
                        modifier = Modifier.height(350.dp),
                        model = series.image,
                        contentScale = ContentScale.Crop,
                        contentDescription = "image of ${series.title}"
                    )
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text(
                            text = series.title,
                            fontSize = 14.sp,
                            fontFamily = openSansFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = DarkBlue
                        )
                        Spacer.Vertical.Default()
                        Text(
                            text = series.description,
                            fontFamily = openSansFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            color = DarkBlue
                        )

                        Spacer.Vertical.Small()

                    }

                }

                SeriesDetailsState.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}