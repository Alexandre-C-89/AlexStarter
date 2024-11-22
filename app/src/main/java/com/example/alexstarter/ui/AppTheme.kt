package com.example.alexstarter.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.alexstarter.ui.custom.ColorPalette
import com.example.alexstarter.ui.custom.darkColorPalette
import com.example.alexstarter.ui.custom.lightColorPalette

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorPalette = if (isDarkTheme) darkColorPalette else lightColorPalette

    CompositionLocalProvider(
        LocalDarkTheme provides isDarkTheme,
        content = content
    )

}

object AppTheme {

    val colorPalette: ColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalColorPalette.current

    val isDarkTheme: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalDarkTheme.current

}

val LocalColorPalette = staticCompositionLocalOf { lightColorPalette }

val LocalDarkTheme = staticCompositionLocalOf { false }