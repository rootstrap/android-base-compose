package com.rootstrap.tv.pages.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rootstrap.tv.data.MoviesRepository
import com.rootstrap.tv.navigation.MainScreens
import com.rootstrap.tv.pages.home.HomeScreenViewModel
import com.rootstrap.tv.pages.home.HorizontalRowWithTitle

@Composable
fun MoviesScreen(navHostController: NavHostController) {
    // TODO: use some DI framework to inject the view model
    val viewModel = remember { HomeScreenViewModel(videoRepository = MoviesRepository()) }
    val uiState by viewModel.uiStateFlow.collectAsState()

    // This shouldn't be here after DI framework is implemented
    LaunchedEffect(key1 = true) {
        viewModel.onHomeScreenLoaded()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        ImmersiveListExample(
            modifier = Modifier,
            featuredMovies = uiState.featuredMovies
        )

        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            uiState.homeRows.forEach() { row ->
                HorizontalRowWithTitle(
                    rowName = row.name,
                    rowItems = row.rowItems,
                    onItemClick = { movie ->
                        navHostController.navigate(
                            MainScreens.DetailScreen.detailScreenRoute(
                                movie?.id ?: ""
                            )
                        )
                    }
                )
            }
        }
    }
}