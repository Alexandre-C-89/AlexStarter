package com.example.alexstarter.designsystem.card

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.alexstarter.domain.series.model.Series
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.White

@Composable
fun CardSeriesItem(
    onClick: () -> Unit,
    series: Series
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = DarkBlue
        )
    ) {
        AsyncImage(
            model = series.posterPath,
            contentScale = ContentScale.Crop,
            contentDescription = "image from series"
        )
    }
}