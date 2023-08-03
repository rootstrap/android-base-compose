package com.rootstrap.tv.pages.movies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.rootstrap.tv.data.MoviesRepository
import com.rootstrap.tv.navigation.MainScreens
import com.rootstrap.tv.pages.home.HomeScreenViewModel
import com.rootstrap.tv.pages.home.HorizontalRowWithTitle
import com.rootstrap.tv.theme.Dimens

@Composable
fun MoviesScreen(navHostController: NavHostController) {
    // TODO: use some DI framework to inject the view model
    val viewModel = remember { HomeScreenViewModel(videoRepository = MoviesRepository()) }
    val uiState by viewModel.uiStateFlow.collectAsState()

    // This shouldn't be here after DI framework is implemented
    LaunchedEffect(key1 = true) {
        viewModel.onHomeScreenLoaded()
    }
    TvLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = Dimens.paddingTenQuarters)
    ) {
        item {
            ImmersiveListExample(
                modifier = Modifier,
                featuredMovies = uiState.featuredMovies
            )
        }

        items(uiState.homeRows.size) {
            val row = uiState.homeRows[it]
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
