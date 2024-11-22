package com.example.alexstarter.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.alexstarter.ui.AppTheme
import com.example.alexstarter.ui.theme.DarkBlue

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        modifier = modifier,
        containerColor = AppTheme.colorPalette.surfacePrimary,
        contentColor = AppTheme.colorPalette.textPrimary
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
        ) {
            content()
        }
    }
}