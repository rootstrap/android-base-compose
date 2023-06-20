package com.rootstrap.androidcomposebase.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.rootstrap.androidcomposebase.ui.pages.main.HomeScreen
import com.rootstrap.androidcomposebase.ui.utils.Globals

fun NavGraphBuilder.mainGraph() {
    navigation(
        route = RoutesApp.Main.route,
        startDestination = RoutesMain.Home.route
    ) {
        composable(
            RoutesMain.Home.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = Globals.baseDeepLink },
                navDeepLink { uriPattern = Globals.deepLinkWithIdExample },
            )
        ) { entry ->
            //This is a dummy example
            val someId =
                entry.arguments?.containsKey("id")?.run { entry.arguments?.getString("id") }
            HomeScreen(someId)
        }
    }
}

sealed class RoutesMain(val route: String) {
    object Home : RoutesOnBoarding("home")
}
