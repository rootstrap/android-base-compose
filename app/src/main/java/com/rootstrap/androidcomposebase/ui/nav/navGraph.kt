package com.rootstrap.androidcomposebase.ui.nav

import androidx.compose.runtime.Composable

/**
 * Split nav graph depends on the auth state
 * **/
@Composable
fun AppNav(isLoggedIn: Boolean) {
    if (isLoggedIn) {
        NavMain()
    } else {
        NavOnBoarding()
    }
}
