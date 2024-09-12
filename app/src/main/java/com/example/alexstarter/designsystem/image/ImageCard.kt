package com.example.alexstarter.designsystem.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.text.Text
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun ImageCardItem(
    image: String,
    text: String
) {
    Column(
        modifier = Modifier.width(80.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(6.dp)
        ) {
            AsyncImage(
                modifier = Modifier.height(80.dp),
                model = image,
                contentDescription = "image od actor",
                contentScale = ContentScale.Crop
            )
        }
        Spacer.Vertical.Small()
        Text(
            text = text,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontFamily = openSansFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkBlue
            )
        )
    }
}

@Preview
@Composable
fun ImageCardItemPreview() {
    ImageCardItem(
        image = "",
        "Pierre Lucini"
    )
}