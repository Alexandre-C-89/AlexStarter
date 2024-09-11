package com.example.alexstarter.designsystem.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

object Title {

    @Composable
    fun Big(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            textAlign = TextAlign.Start,
            color = DarkBlue
        )
    )

    @Composable
    fun Medium(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            color = DarkBlue
        )
    )

    @Composable
    fun Default(text: String) = Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily,
            color = DarkBlue
        )
    )

}