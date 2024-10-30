package com.rootstrap.yourAppName.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rootstrap.yourAppName.ui.pages.error.ErrorPage
import com.rootstrap.yourAppName.ui.pages.main.MainPage
import com.rootstrap.yourAppName.ui.theme.AppData

@Composable
fun MainNavHost() {
    NavHost(
        navController = AppData.mainNavController,
        startDestination = MainRoutes.MainPage.route
    ) {
        composable(route = MainRoutes.MainPage.route) { MainPage() }
        composable(
            route = MainRoutes.ErrorPage.route,
            arguments = listOf(navArgument("message") { type = NavType.StringType })
        ) { backStackEntry ->
            ErrorPage(message = backStackEntry.arguments?.getString("message") ?: "404")
        }
    }
}
