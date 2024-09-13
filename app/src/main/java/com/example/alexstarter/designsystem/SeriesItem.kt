package com.example.alexstarter.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.alexstarter.domain.series.model.Series

@Composable
fun SeriesItem(
    onClick: () -> Unit,
    series: Series
) {
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(6.dp),
    ) {
        AsyncImage(
            model = series.posterPath,
            contentScale = ContentScale.Crop,
            contentDescription = "image movie"
        )
    }
}