package com.rootstrap.bottomnavapp.ui.base.navigation

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rootstrap.bottomnavapp.ui.pages.DetailScreen
import com.rootstrap.bottomnavapp.ui.pages.HomeScreen
import com.rootstrap.bottomnavapp.ui.pages.NotificationScreen
import com.rootstrap.bottomnavapp.ui.pages.ProfileScreen
import com.rootstrap.bottomnavapp.ui.pages.SplashScreen

@Composable
fun BottomBarNavigation(
    navHostController: NavHostController,
    padding: PaddingValues,
    context: Activity
) {

    NavHost(
        navController = navHostController, startDestination = ScreenRoutes.Splash.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(ScreenRoutes.Splash.route) {
            SplashScreen(navHostController = navHostController)
        }
        navigation(
            route = ScreenRoutes.BottomBar.route,
            startDestination = BottomBarRoutes.HOME.routes
        ) {
            composable(BottomBarRoutes.HOME.routes) {
                HomeScreen(navHostController = navHostController,context)
            }
            composable(BottomBarRoutes.NOTIFICATION.routes) {
                NotificationScreen(navHostController = navHostController)
            }
            composable(BottomBarRoutes.PROFILE.routes) {
                ProfileScreen(navHostController = navHostController)
            }
        }
        composable(ScreenRoutes.Detail.route) {
            DetailScreen(navHostController = navHostController)
        }
    }

}