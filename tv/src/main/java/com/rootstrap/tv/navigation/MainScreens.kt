package com.rootstrap.tv.navigation

const val ARG_MOVIE_ID = "movie_id"
const val ARG_MOVIE_URL = "movie_url"

sealed class MainScreens(val title: String) {
    object Home : MainScreens("home_screen")
    object Player : MainScreens("player_screen/{$ARG_MOVIE_URL}") {
        fun playerScreenRoute(movieUrl: String) = "player_screen/$movieUrl"
    }

    object DetailScreen : MainScreens("detail_screen/{$ARG_MOVIE_ID}") {
        fun detailScreenRoute(movieId: String) = "detail_screen/$movieId"
    }
}