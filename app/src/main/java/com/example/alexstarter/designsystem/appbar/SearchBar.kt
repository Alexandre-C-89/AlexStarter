package com.example.alexstarter.designsystem.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    isSearching: Boolean,
    onActiveChanged: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
) {
    androidx.compose.material3.SearchBar(
        query = query,//text showed on SearchBar
        onQueryChange = onQueryChange, //update the value of searchText
        onSearch = onSearch, //the callback to be invoked when the input service triggers the ImeAction.Search action
        active = isSearching, //whether the user is searching or not
        onActiveChange = onActiveChanged, //the callback to be invoked when this search bar's active state is changed
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

    }
}