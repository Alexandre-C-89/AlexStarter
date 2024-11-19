package com.example.alexstarter.designsystem.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alexstarter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCarousel(

){
    val carouselState = rememberCarouselState{3}

    HorizontalMultiBrowseCarousel(
        state = carouselState,
        preferredItemWidth = 300.dp,
        itemSpacing = 10.dp
    ) { page ->
        Box(modifier = Modifier.size(300.dp)) {
            Image(
                painter = painterResource(
                    id = when (page) {
                        0 -> R.drawable.candidature_spontanee
                        1 -> R.drawable.capture
                        else -> R.drawable.capture_2
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun AppCarouselPreview(){
    AppCarousel()
}