package com.example.alexstarter.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.appbar.SearchBar
import com.example.alexstarter.designsystem.appbar.TopBar
import com.example.alexstarter.designsystem.tab.TabRowApp

@Composable
fun SearchRoute(
    navController: NavController,
    //viewModel: SearchViewModel = hiltViewModel(),
    onNavigateClick: (String) -> Unit
 ){
    //val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    SearchScreen(
        //searchState = searchState,
        onNavigateClick = onNavigateClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateClick: (String) -> Unit
){
    AppScaffold(
        topBar = {
            Column {
                TopBar()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            /*TabRowApp(
                modifier = Modifier.weight(1f),
                onNavigateClick = onNavigateClick
            )*/
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "SearchScreen")
            }
        }
    }
}