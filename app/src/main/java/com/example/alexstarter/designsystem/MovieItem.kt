package com.example.alexstarter.designsystem

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

    Card(
        modifier = Modifier
            .width(305.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(DarkBlue)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Title: ${movie.title}",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))

            AsyncImage(
                model = movie.image,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp),
                contentDescription = "image movie"
            )


            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

}