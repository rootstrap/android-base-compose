package com.rootstrap.tv.pages.home.navigationdrawer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.rootstrap.tv.pages.home.navigationdrawer.MenuItem

sealed class NestedScreens(val title: String) {
    object Home : NestedScreens("Home")
    object Search : NestedScreens("Search")
    object Favorites : NestedScreens("Favourites")
    object Movies : NestedScreens("Movies")

    companion object {
        fun values(): List<MenuItem> =
            listOf(
                MenuItem(id = Home.title, Home.title, Icons.Default.Home),
                MenuItem(id = Search.title, Search.title, Icons.Default.Search),
                MenuItem(id = Favorites.title, Favorites.title, Icons.Default.Favorite),
                MenuItem(id = Movies.title, Movies.title, Icons.Default.Face)
            )
    }
}
