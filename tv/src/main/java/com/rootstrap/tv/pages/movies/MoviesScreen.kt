package com.rootstrap.tv.pages.movies

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.rootstrap.tv.navigation.MainScreens
import com.rootstrap.tv.pages.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Composable
fun MoviesScreen(navHostController: NavHostController) {
    val context = LocalContext.current as ComponentActivity
    val viewModel = context.getViewModel<HomeScreenViewModel>()
    val uiState by viewModel.uiStateFlow.collectAsState()

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
