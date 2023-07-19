package com.rootstrap.tv.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rootstrap.tv.pages.detail.DetailScreen
import com.rootstrap.tv.pages.home.HomeScreen
import com.rootstrap.tv.pages.player.VideoPlayerScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home.title) {

        composable(
            Screens.Home.title,
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screens.Player.title,
            arguments = listOf(navArgument(ARG_MOVIE_URL) { type = NavType.StringType })
        ) { entry ->
            VideoPlayerScreen(
                mediaUrl = entry.arguments?.getString(ARG_MOVIE_URL) ?: "",
                onBackPressed = { navController.navigateUp() }
            )
        }

        composable(
            route = Screens.DetailScreen.title,
            arguments = listOf(navArgument(ARG_MOVIE_ID) { type = NavType.StringType })
        ) { entry ->
            DetailScreen(
                movieId = entry.arguments?.getString(ARG_MOVIE_ID) ?: "",
                onBackPressed = { navController.navigateUp() },
                onPlayNowClick = { navController.navigate(Screens.Player.title) }
            )
        }
    }
}
