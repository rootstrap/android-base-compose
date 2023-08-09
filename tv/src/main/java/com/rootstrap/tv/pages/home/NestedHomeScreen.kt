package com.rootstrap.tv.pages.home

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.rootstrap.tv.navigation.MainScreens
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Composable
fun NestedHomeScreen(appNavHostController: NavHostController) {
    val context = LocalContext.current as ComponentActivity
    val viewModel = context.getViewModel<HomeScreenViewModel>()
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.onHomeScreenLoaded()
    }
    HomeGrid(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onItemClick = {
            appNavHostController.navigate(
                MainScreens.Player.playerScreenRoute(
                    it?.id ?: ""
                )
            )
        },
    )
}
