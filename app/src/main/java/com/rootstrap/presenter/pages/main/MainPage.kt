package com.rootstrap.presenter.pages.main

import InfoPage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rootstrap.presenter.pages.home.HomePage
import com.rootstrap.presenter.pages.main.components.AppBottomBar
import com.rootstrap.presenter.pages.main.components.DrawerItem

/**
 * This is an example fo a classic drawer layout
 * **/
@Composable
fun MainPage() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomBar(navController) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
        NavHost(
            navController = navController,
            startDestination = DrawerItem.Home.route
        ) {
            composable(DrawerItem.Home.route) { HomePage() }
            composable(DrawerItem.Info.route) { InfoPage() }
        }
    }
}
