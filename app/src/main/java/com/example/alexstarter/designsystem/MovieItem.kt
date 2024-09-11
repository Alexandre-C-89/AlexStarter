package com.example.alexstarter.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.alexstarter.domain.movie.model.Movie

@Composable
fun MovieItem(
    onClick: () -> Unit,
    movie: Movie
) {
    Card(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(6.dp),
    ) {
        AsyncImage(
            model = movie.image,
            contentScale = ContentScale.Crop,
            contentDescription = "image movie"
        )
    }
}