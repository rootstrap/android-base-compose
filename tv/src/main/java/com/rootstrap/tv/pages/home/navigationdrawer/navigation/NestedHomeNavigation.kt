package com.rootstrap.tv.pages.home.navigationdrawer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rootstrap.tv.pages.favourites.FavouritesScreen
import com.rootstrap.tv.pages.home.HomeScreenViewModel
import com.rootstrap.tv.pages.home.NestedHomeScreen
import com.rootstrap.tv.pages.movies.MoviesScreen
import com.rootstrap.tv.pages.search.SearchScreen
import org.koin.androidx.compose.koinViewModel

/**
 * This NavHost represents the nested navigation of the navigation drawer in the home page
 * */
@Composable
fun NestedHomeNavigation(
    appNavHostController: NavHostController,
    nestedNavController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    NavHost(
        navController = nestedNavController,
        startDestination = NestedScreens.Home.title
    ) {
        composable(NestedScreens.Home.title) {
            NestedHomeScreen(
                appNavHostController = appNavHostController,
                viewModel = viewModel
            )
        }
        composable(NestedScreens.Favorites.title) {
            FavouritesScreen(
                navHostController = appNavHostController,
                viewModel = viewModel
            )
        }
        composable(NestedScreens.Search.title) {
            SearchScreen()
        }
        composable(NestedScreens.Movies.title) {
            MoviesScreen(
                navHostController = appNavHostController,
                viewModel = viewModel
            )
        }
    }
}
