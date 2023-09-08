package com.rootstrap.tv.pages.home

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rootstrap.tv.pages.home.navigationdrawer.HomeNavigationDrawer
import com.rootstrap.tv.pages.home.navigationdrawer.navigation.NestedHomeNavigation

@Composable
fun HomeScreen(appNavController: NavHostController) {
    val nestedNavController = rememberNavController()
    Row {
        HomeNavigationDrawer(
            content = {
                NestedHomeNavigation(
                    appNavHostController = appNavController,
                    nestedNavController = nestedNavController
                )
            },
            onMenuSelected = {
                nestedNavController.popBackStack()
                nestedNavController.navigate(it.id)
            },
            onBackPressed = {
                appNavController.popBackStack()
                nestedNavController.popBackStack()
            }
        )
    }
}
