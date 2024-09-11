package com.example.alexstarter.designsystem.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.openSansFontFamily

object Text {

    @Composable
    fun Big(text: String) = Text(
        text = text,
        style = TextStyle(
            fontFamily = openSansFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBlue
        )
    )

    @Composable
    fun Medium(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontFamily = openSansFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBlue
        )
    )

    @Composable
    fun Default(text: String) = Text(
        text = text,
        style = TextStyle(
            fontFamily = openSansFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBlue
        )
    )

}