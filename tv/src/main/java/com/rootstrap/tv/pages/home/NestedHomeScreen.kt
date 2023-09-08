package com.rootstrap.tv.pages.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.rootstrap.tv.navigation.MainScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun NestedHomeScreen(
    appNavHostController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    HomeGrid(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onItemClick = {
            appNavHostController.navigate(
                MainScreens.Player.playerScreenRoute(
                    movieId = it?.id.orEmpty()
                )
            )
        }
    )
}
