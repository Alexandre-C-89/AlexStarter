package com.example.alexstarter.feature.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alexstarter.MovieListItem
import com.example.alexstarter.MovieViewModel
import com.example.alexstarter.domain.model.Movie
import com.example.alexstarter.util.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.movies.collectAsStateWithLifecycle()
    HomeScreen(
        state = uiState
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    state: Resource<List<Movie>>
) {

    val context = LocalContext.current
    //val getMovies = viewModel.getMoviesData

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(15.dp)
                ) {
                    Text(
                        text = "User Live Data",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                LaunchedEffect(key1 = true) {

                    if (state is Resource.Success) {
                        Toast.makeText(context, "Fetching data success!", Toast.LENGTH_SHORT).show()
                    } else if (state is Resource.Error) {
                        Toast.makeText(context, "Error: ${state.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                when(state){
                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Resource.Success -> {
                        LazyColumn(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            items(state.data!!.size) { index ->
                                MovieListItem(movie = state.data[index])
                            }
                        }
                    }
                }
            }
        }
    }

    /*when (state) {
        is Resource.Error -> Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Erreur : ${state.message}")
        }

        is Resource.Loading -> CircularProgressIndicator()

        is Resource.Success ->
        {
            state.data?.let { movieList ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(movieList) { movie ->
                        Text(text = movie.title)
                    }
                }
            } ?: run {
                // Optionnel: Afficher un message si la liste est null
                Text(text = "Aucun film disponible", modifier = Modifier.fillMaxSize())
            }
        }

    }*/

}