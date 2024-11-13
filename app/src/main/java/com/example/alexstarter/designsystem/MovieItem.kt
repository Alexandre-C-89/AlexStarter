package com.example.alexstarter.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alexstarter.domain.movie.model.Movie
import com.example.alexstarter.ui.theme.White

@Composable
fun MovieItem(
    onClick: () -> Unit,
    movie: Movie
) {
    Box(
        modifier = Modifier.clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        Card(
            shape = RoundedCornerShape(6.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .width(300.dp)
                    .height(170.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(6.dp)),
                model = movie.image,
                contentScale = ContentScale.Crop,
                contentDescription = "image movie"
            )
        }
        Text(
            modifier = Modifier
                .widthIn(min = 150.dp, max = 235.dp)
                .padding(8.dp),
            text = movie.title,
            color = White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
    }
}