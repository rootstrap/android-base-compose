package com.rootstrap.androidcomposebase.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/**
 * Split nav graph depends on the auth state
 * **/
@Composable
fun AppNav(isLoggedIn: Boolean) {
    val navController = rememberNavController()
    val initialRoute = if (isLoggedIn) RoutesApp.Main else RoutesApp.OnBoarding
    NavHost(
        navController = navController,
        startDestination = initialRoute.route,
    ) {
        onBoardingGraph()
        mainGraph()
    }
}

sealed class RoutesApp(val route: String) {
    object Main : RoutesApp("main")
    object OnBoarding : RoutesApp("onBoarding")
}
