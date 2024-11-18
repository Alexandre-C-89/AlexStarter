package com.example.alexstarter.designsystem.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.domain.series.model.Series
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.White

@Composable
fun CardSeriesItem(
    onClick: () -> Unit,
    series: Series
) {
    Card(
        modifier = Modifier
            .widthIn(min = 250.dp, 300.dp)
            .height(155.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = DarkBlue
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            AsyncImage(
                model = series.posterPath,
                contentScale = ContentScale.Inside,
                contentDescription = "image from series"
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
            ){
                Text(
                    text = series.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer.Vertical.Small()
                Text(
                    text = series.overview,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    maxLines = 3,
                    overflow = TextOverflow.Clip
                )
                Spacer.Vertical.Small()
                Text(
                    text = series.type ?: "types : no",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Light
                )
            }

        }
    }
}