package com.example.alexstarter.designsystem.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alexstarter.designsystem.indicator.CircularIndicator

@Composable
fun TitleWithRow(
    text: String,
    progress: Float
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Title.Medium(text = text)
        CircularIndicator(percentage = progress)
    }
}

@Preview
@Composable
fun TitleWithRowPreview(){
    TitleWithRow(
        text = "Deadpoll",
        progress = 0.7F
    )
}