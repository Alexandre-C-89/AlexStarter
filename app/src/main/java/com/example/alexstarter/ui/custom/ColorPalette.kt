package com.example.alexstarter.ui.custom

import androidx.compose.ui.graphics.Color
import com.example.alexstarter.ui.theme.Black
import com.example.alexstarter.ui.theme.Blue
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.Grey40
import com.example.alexstarter.ui.theme.LightMintheGreen
import com.example.alexstarter.ui.theme.LightOrange
import com.example.alexstarter.ui.theme.MintheGreen
import com.example.alexstarter.ui.theme.OffWhite
import com.example.alexstarter.ui.theme.Orange
import com.example.alexstarter.ui.theme.Success
import com.example.alexstarter.ui.theme.White
import com.example.alexstarter.ui.theme.Error
import com.example.alexstarter.ui.theme.LightBlue

data class ColorPalette(
    val textPrimary: Color,
    val textSecondary: Color,
    val textSuccess: Color,
    val textError: Color,
    val surfacePrimary: Color,
    val surfaceSecondary: Color,
    val iconSecondary: Color,
    val iconPrimary: Color,
    val iconSuccess: Color,
    val iconError: Color,

    val buttonPrimary: Color,
    val buttonSecondary: Color,
    val buttonSuccess: Color,
    val buttonError: Color,

    val borderPrimary: Color,
    val borderSecondary: Color,

    )

val darkColorPalette = ColorPalette(
    textPrimary = White,
    textSecondary = Grey40,
    textSuccess = Success,
    textError = Error,
    surfacePrimary = DarkBlue,
    surfaceSecondary = Blue,
    iconPrimary = White,
    iconSecondary = LightBlue,
    iconSuccess = Success,
    iconError = Error,

    buttonPrimary = LightBlue,
    buttonSecondary = Blue,
    buttonSuccess = Success,
    buttonError = Error,

    borderPrimary = White,
    borderSecondary = LightBlue,
)

val lightColorPalette = ColorPalette(
    textPrimary = Black,
    textSecondary = Grey40,
    textSuccess = Success,
    textError = Error,
    surfacePrimary = DarkBlue,
    surfaceSecondary = LightBlue,
    iconPrimary = White,
    iconSecondary = LightBlue,
    iconSuccess = Success,
    iconError = Error,

    buttonPrimary = White,
    buttonSecondary = LightBlue,
    buttonSuccess = Success,
    buttonError = Error,

    borderPrimary = White,
    borderSecondary = Blue,
)