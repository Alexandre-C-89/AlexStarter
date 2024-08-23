package com.example.alexstarter.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alexstarter.ui.theme.AlexStarterTheme
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(

) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "AlexMovie")
            }
        },
        modifier = Modifier.height(70.dp),
        colors = TopAppBarColors(
            containerColor = DarkBlue,
            titleContentColor = White,
            actionIconContentColor = White,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = White
        )
        //navigationIcon: @Composable () -> Unit = {},
        //actions: @Composable RowScope.() -> Unit = {},
    )
}

@Preview
@Composable
fun TopBarPreview() {
    AlexStarterTheme {
        TopBar()
    }
}