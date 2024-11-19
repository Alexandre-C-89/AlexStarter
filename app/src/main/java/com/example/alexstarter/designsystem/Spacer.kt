package com.example.alexstarter.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

object Spacer {
    object Vertical {

        @Composable
        private fun VerticalSpacer(size: Dp) = Spacer(modifier = Modifier.height(size))

        @Composable
        fun Small() = VerticalSpacer(size = Spacings.Small)

        @Composable
        fun Default() = VerticalSpacer(size = Spacings.Default)

        @Composable
        fun Medium() = VerticalSpacer(size = Spacings.Medium)

        @Composable
        fun Large() = VerticalSpacer(size = Spacings.Large)

        @Composable
        fun ExtraLarge() = VerticalSpacer(size = Spacings.ExtraLarge)

        @Composable
        fun Tiny() = VerticalSpacer(size = Spacings.Tiny)
    }

    object Horizontal {

        @Composable
        private fun HorizontalSpacer(size: Dp) = Spacer(modifier = Modifier.width(size))

        @Composable
        fun Mega() = HorizontalSpacer(size = Spacings.Mega)

        @Composable
        fun Large() = HorizontalSpacer(size = Spacings.Large)

        @Composable
        fun Medium() = HorizontalSpacer(size = Spacings.Medium)

        @Composable
        fun Default() = HorizontalSpacer(size = Spacings.Default)

        @Composable
        fun Small() = HorizontalSpacer(size = Spacings.Small)

    }
}