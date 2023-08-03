package com.rootstrap.tv.pages.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.rootstrap.tv.data.MoviesRepository
import com.rootstrap.tv.navigation.MainScreens
import com.rootstrap.tv.pages.home.HomeScreenViewModel

@Composable
fun MoviesScreen(navHostController: NavHostController) {
    // TODO: use some DI framework to inject the view model
    val viewModel = remember { HomeScreenViewModel(videoRepository = MoviesRepository()) }
    val uiState by viewModel.uiStateFlow.collectAsState()

    // This shouldn't be here
    LaunchedEffect(key1 = true) {
        viewModel.onHomeScreenLoaded()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ImmersiveListExample(
            modifier = Modifier,
            featuredMovies = uiState.featuredMovies,
            onItemClick = { movie ->
                navHostController.navigate(
                    MainScreens.DetailScreen.detailScreenRoute(
                        movie.id
                    )
                )
            }
        )
    }
}