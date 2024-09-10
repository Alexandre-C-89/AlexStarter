package com.example.alexstarter.designsystem.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alexstarter.ui.theme.DarkBlue
import com.example.alexstarter.util.TabItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowApp(
    modifier: Modifier,
    onNavigateClick: (String) -> Unit
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItem.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabItem.forEachIndexed { index, tabItem ->

            Tab(
                modifier = Modifier.height(40.dp),
                selected = index == selectedTabIndex,
                onClick = {
                    selectedTabIndex = index
                    onNavigateClick(tabItem.title)
                },
                text = { Text(text = tabItem.title, color = DarkBlue) }
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
    ) { index ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = tabItem[index].title)
        }
    }
}

val tabItem = listOf(
    TabItem(
        title = "Home"
    ), TabItem(
        title = "Search"
    ), TabItem(
        title = "Settings"
    )
)