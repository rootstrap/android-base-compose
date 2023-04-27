package com.rootstrap.presenter.pages.main.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.Text
import com.rootstrap.app.R


sealed class DrawerItem(val route: String, @DrawableRes val icon: Int, @StringRes val label: Int) {
    object Home : DrawerItem("home", R.drawable.baseline_home_24, R.string.label_drawer_home)
    object Info : DrawerItem("Info", R.drawable.baseline_info_24, R.string.label_drawer_info)
}

@Composable
fun AppBottomBar(navController: NavController) {
    val tabs = listOf(DrawerItem.Home, DrawerItem.Info)
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = Color.Transparent
    ) {
        tabs.forEach { item ->
            val isSelected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(id = item.label))
                })
        }
    }
}