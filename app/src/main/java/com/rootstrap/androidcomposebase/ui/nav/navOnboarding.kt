package com.rootstrap.androidcomposebase.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.rootstrap.androidcomposebase.ui.pages.login.LogInScreen
import com.rootstrap.androidcomposebase.ui.utils.Globals

@Composable
fun NavOnBoarding() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RoutesOnBoarding.Login.route,
    ) {
        composable(
            RoutesOnBoarding.Login.route,
            deepLinks = listOf(navDeepLink { uriPattern = Globals.baseDeepLink })
        ) {
            LogInScreen()
        }
    }
}

sealed class RoutesOnBoarding(val route: String) {
    object Login : RoutesOnBoarding("login")
}
