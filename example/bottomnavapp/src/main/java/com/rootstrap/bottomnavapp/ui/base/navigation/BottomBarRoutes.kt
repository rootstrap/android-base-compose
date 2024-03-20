package com.rootstrap.bottomnavapp.ui.base.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rootstrap.example.bottomnavapp.R

private const val HOME_ROUTE = "/home"
private const val NOTIFICATION_ROUTE = "/notification"
private const val PROFILE_ROUTE = "/profile"

enum class BottomBarRoutes(
    val id: Int,
    @StringRes val title: Int,
    val routes: String,
    @DrawableRes val icon: Int
) {

    HOME(1, R.string.home, HOME_ROUTE, R.drawable.home),
    NOTIFICATION(2, R.string.notification, NOTIFICATION_ROUTE, R.drawable.notification),
    PROFILE(3, R.string.profile, PROFILE_ROUTE, R.drawable.profile)

}