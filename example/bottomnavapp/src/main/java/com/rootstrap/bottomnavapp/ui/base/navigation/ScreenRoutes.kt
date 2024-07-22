package com.rootstrap.bottomnavapp.ui.base.navigation

private const val SCREEN_ROUTE_SPLASH = "/splash"
private const val SCREEN_ROUTE_BOTTOMBAR = "/bottombar"
private const val SCREEN_ROUTE_DETAIL = "/detail"

sealed class ScreenRoutes(val route: String) {
    data object Splash : ScreenRoutes(SCREEN_ROUTE_SPLASH)
    data object BottomBar : ScreenRoutes(SCREEN_ROUTE_BOTTOMBAR)
    data object Detail : ScreenRoutes(SCREEN_ROUTE_DETAIL)
}