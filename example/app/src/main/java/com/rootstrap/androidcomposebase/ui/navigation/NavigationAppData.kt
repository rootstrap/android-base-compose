package com.rootstrap.androidcomposebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

internal val LocalNavigation = staticCompositionLocalOf<NavHostController?> { null }

object NavigationAppData {
    val navController: NavHostController
        @Composable
        @ReadOnlyComposable
        get() = LocalNavigation.current!!
}
