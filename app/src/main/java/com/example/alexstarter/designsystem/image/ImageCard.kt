package com.example.alexstarter.designsystem.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.alexstarter.R

@Composable
fun ImageCardItem(
    image: String
) {
    Card(
        modifier = Modifier
            .height(60.dp)
            .width(60.dp),
        shape = RoundedCornerShape(100)
    ) {
        AsyncImage(
            model = image,
            contentDescription = "image od actor",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun ImageCardItemPreview() {
    ImageCardItem(
        image = ""
    )
}