package com.example.alexstarter.designsystem.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alexstarter.designsystem.indicator.CircularIndicator
import com.example.alexstarter.ui.theme.White
import com.example.alexstarter.ui.theme.openSansFontFamily

@Composable
fun TitleWithRow(
    text: String,
    //progress: Float
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = openSansFontFamily,
                color = White
            )
        )
        //CircularIndicator(percentage = progress)
    }
}

@Preview
@Composable
fun TitleWithRowPreview(){
    TitleWithRow(
        text = "Deadpoll",
        //progress = 0.7F
    )
}