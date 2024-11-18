package com.example.alexstarter.designsystem.text

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alexstarter.ui.theme.Orange
import com.example.alexstarter.ui.theme.White
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun TextWithThumbnail(
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(Orange),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = text,
            style = TextStyle(
                fontFamily = openSansFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = White
            )
        )
    }
}

@Preview
@Composable
fun TextWithThumbnailPreview() {
    TextWithThumbnail(
        text = "Fiction"
    )
}