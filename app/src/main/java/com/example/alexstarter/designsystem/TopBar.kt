package com.example.alexstarter.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alexstarter.designsystem.icon.BackIconButton
import com.example.alexstarter.designsystem.icon.MenuIconButton
import com.example.alexstarter.designsystem.icon.SearchIconButton
import com.example.alexstarter.ui.theme.AlexStarterTheme
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    shadowElevation: Dp = 0.dp,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onBackClick: (() -> Unit)? = null,
) {
    Surface(shadowElevation = shadowElevation) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            title = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Movie",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Start,
                            color = White
                        )
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkBlue,
                titleContentColor = White,
                actionIconContentColor = White,
                navigationIconContentColor = White
            ),
            navigationIcon = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onNavigationClick?.let {
                        MenuIconButton { onNavigationClick() }
                    }
                }
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onBackClick?.let {
                        BackIconButton { onBackClick() }
                    }
                }
            },
            actions = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onSearchClick?.let {
                        SearchIconButton { onSearchClick() }
                    }
                }
            },
            scrollBehavior = scrollBehavior
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarPreview() {
    AlexStarterTheme {
        TopBar(
            shadowElevation = 0.dp,
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                rememberTopAppBarState()
            ),
            onNavigationClick = { },
            onSearchClick = { },
            onBackClick = { },
        )
    }
}