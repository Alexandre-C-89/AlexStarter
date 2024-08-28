package com.example.alexstarter.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.White

@Composable
fun MovieItem(
    movie: Movie
) {
    AsyncImage(
        model = movie.image,
        contentScale = ContentScale.Crop,
        modifier = Modifier.background(shape = RoundedCornerShape(6.dp), color = Color.Transparent),
        contentDescription = "image movie"
    )
}