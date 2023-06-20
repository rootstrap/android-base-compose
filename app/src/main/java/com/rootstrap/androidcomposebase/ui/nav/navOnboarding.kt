package com.rootstrap.androidcomposebase.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.rootstrap.androidcomposebase.ui.pages.login.LogInScreen
import com.rootstrap.androidcomposebase.ui.utils.Globals

fun NavGraphBuilder.onBoardingGraph() {
    navigation(
        route = RoutesApp.OnBoarding.route,
        startDestination = RoutesOnBoarding.Login.route
    ) {
        composable(
            RoutesOnBoarding.Login.route,
            deepLinks = listOf(navDeepLink { uriPattern = Globals.baseDeepLink })
        ) { LogInScreen() }
    }
}

sealed class RoutesOnBoarding(val route: String) {
    object Login : RoutesOnBoarding("login")
}
