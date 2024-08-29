package com.example.alexstarter.designsystem.icon

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.alexstarter.R

@Composable
fun MenuIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "menu button"
        )
    }
}

@Composable
fun SearchIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search button"
        )
    }
}

@Composable
fun BackIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back button"
        )
    }
}