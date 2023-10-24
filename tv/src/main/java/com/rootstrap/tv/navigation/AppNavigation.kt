package com.rootstrap.tv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rootstrap.tv.pages.detail.DetailScreen
import com.rootstrap.tv.pages.home.HomeScreen
import com.rootstrap.tv.player.VideoPlayerScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreens.Home.title) {
        composable(
            MainScreens.Home.title
        ) {
            HomeScreen(navController)
        }

        composable(
            route = MainScreens.Player.title,
            arguments = listOf(navArgument(ARG_MOVIE_ID) { type = NavType.StringType })
        ) { entry ->
            VideoPlayerScreen(
                movieId = entry.arguments?.getString(ARG_MOVIE_ID) ?: "",
                onBackPressed = { navController.navigateUp() }
            )
        }

        composable(
            route = MainScreens.DetailScreen.title,
            arguments = listOf(navArgument(ARG_MOVIE_ID) { type = NavType.StringType })
        ) { entry ->
            val movieId = entry.arguments?.getString(ARG_MOVIE_ID) ?: ""
            DetailScreen(
                movieId = movieId,
                onBackPressed = { navController.navigateUp() },
                onPlayNowClick = {
                    navController.navigate(
                        MainScreens.Player.playerScreenRoute(
                            movieId
                        )
                    )
                }
            )
        }
    }
}
