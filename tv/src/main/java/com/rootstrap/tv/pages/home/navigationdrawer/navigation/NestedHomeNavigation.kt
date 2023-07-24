package com.rootstrap.tv.pages.home.navigationdrawer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rootstrap.tv.pages.favourites.FavouritesScreen
import com.rootstrap.tv.pages.home.NestedHomeScreen
import com.rootstrap.tv.pages.movies.MoviesScreen
import com.rootstrap.tv.pages.search.SearchScreen

/**
 * This NavHost represents the nested navigation of the navigation drawer in the home page
 * */
@Composable
fun NestedHomeNavigation(
    appNavHostController: NavHostController,
    nestedNavController: NavHostController
) {
    NavHost(navController = nestedNavController, startDestination = NestedScreens.Home.title) {
        composable(NestedScreens.Home.title) {
            NestedHomeScreen(appNavHostController)
        }
        composable(NestedScreens.Favorites.title) {
            FavouritesScreen()
        }
        composable(NestedScreens.Search.title) {
            SearchScreen()
        }
        composable(NestedScreens.Movies.title){
            MoviesScreen(appNavHostController)
        }
    }
}
