package com.rootstrap.androidcomposebase.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.rootstrap.androidcomposebase.ui.pages.main.MainScreen
import com.rootstrap.androidcomposebase.ui.utils.Globals

@Composable
fun NavMain() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RoutesMain.Main.route,
    ) {
        composable(
            RoutesMain.Main.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = Globals.baseDeepLink },
                navDeepLink { uriPattern = Globals.deepLinkWithIdExample },
            )
        ) { entry ->
            //This is a dummy example
            val someId =
                entry.arguments?.containsKey("id")?.run { entry.arguments?.getString("id") }
            MainScreen(someId)
        }
    }
}

sealed class RoutesMain(val route: String) {
    object Main : RoutesOnBoarding("main")
}
