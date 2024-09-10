package com.example.alexstarter.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

/*@HiltViewModel
class SearchViewModel(

): ViewModel() {

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _dataList = MutableStateFlow(data)
    val countriesList = searchText
        .combine(_dataList) { text, datas ->//combine searchText with _contriesList
            if (text.isBlank()) { //return the entery list of datas if not is typed
                datas
            }
            datas.filter { country ->// filter and return a list of datas based on the text the user typed
                country.uppercase().contains(text.trim().uppercase())
            }
        }.stateIn(//basically convert the Flow returned from combine operator to StateFlow
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),//it will allow the StateFlow survive 5 seconds before it been canceled
            initialValue = _dataList.value
        )

     fun onSearchTextChange(text: String) {
            _searchText.value = text
    }

    fun onToogleSearch() {
            _isSearching.value = !_isSearching.value
            if (!_isSearching.value) {
                onSearchTextChange("")
            }
    }

}*/