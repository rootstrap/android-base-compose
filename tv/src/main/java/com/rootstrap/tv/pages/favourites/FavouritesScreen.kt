package com.rootstrap.tv.pages.favourites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.rootstrap.tv.navigation.MainScreens
import com.rootstrap.tv.pages.home.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouritesScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsState()
    // TODO: inject the same instance of HomeScreenViewModel in MoviesScreen.kt and FavouritesScreen.kt and remove this line
    LaunchedEffect(key1 = true) {
        viewModel.onHomeScreenLoaded()
    }
    FavouritesGrid(
        modifier = Modifier.fillMaxSize(),
        favouriteRows = uiState.favouriteRows,
        onItemClick = { movie ->
            navHostController.navigate(
                MainScreens.DetailScreen.detailScreenRoute(
                    movie?.id.orEmpty()
                )
            )
        })
}
