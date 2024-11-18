package com.example.alexstarter.designsystem.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.White
import com.example.alexstarter.ui.theme.openSansFontFamily

object Title {

    @Composable
    fun Big(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            textAlign = TextAlign.Start,
            color = Color.Black
        )
    )

    @Composable
    fun Medium(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            color = Color.Black
        )
    )

    @Composable
    fun Default(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            color = Color.Black
        )
    )

    @Composable
    fun Small(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            color = Color.Black
        )
    )

}