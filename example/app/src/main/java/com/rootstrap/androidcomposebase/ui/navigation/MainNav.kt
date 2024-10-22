package com.rootstrap.androidcomposebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rootstrap.androidcomposebase.ui.pages.login.LogInScreen
import com.rootstrap.androidcomposebase.ui.pages.settings.SettingsScreen

@Composable
fun MainNavHost(isOSDarkTheme: Boolean, onThemeUpdated: () -> Unit) {
    NavHost(
        navController = NavigationAppData.navController,
        startDestination = Pages.LoginScreen
    ) {
        composable<Pages.LoginScreen> {
            LogInScreen()
        }
        composable<Pages.SettingsScreen> {
            val args = it.toRoute<Pages.SettingsScreen>()
            SettingsScreen(
                email = args.email,
                isOSDarkTheme = isOSDarkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
    }
}
