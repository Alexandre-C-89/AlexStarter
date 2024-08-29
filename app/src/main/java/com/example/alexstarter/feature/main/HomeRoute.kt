package com.example.alexstarter.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alexstarter.designsystem.MovieItem
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.TopBar
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.util.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.movies.collectAsStateWithLifecycle()
    HomeScreen(
        state = uiState,
        onMenuClick = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    state: Resource<List<Movie>>,
    onMenuClick: () -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onMenuClick,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when (state) {
                is Resource.Error -> {}
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.data!!.size) { index ->
                            MovieItem(
                                movie = state.data[index]
                            )
                        }
                    }
                }
            }
        }
    }
}