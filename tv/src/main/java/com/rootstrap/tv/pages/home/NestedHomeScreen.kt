package com.rootstrap.tv.pages.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.rootstrap.tv.data.MoviesRepository
import com.rootstrap.tv.navigation.MainScreens
import com.rootstrap.tv.utils.Constants

@Composable
fun NestedHomeScreen(appNavHostController: NavHostController) {
    val viewModel = remember { HomeScreenViewModel(videoRepository = MoviesRepository()) }
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.onHomeScreenLoaded()
    }
    HomeGrid(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onItemClick = { appNavHostController.navigate(MainScreens.Player.playerScreenRoute(it?.id?:"")) },
    )
}
