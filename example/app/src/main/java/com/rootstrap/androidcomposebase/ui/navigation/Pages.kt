package com.rootstrap.androidcomposebase.ui.navigation

import kotlinx.serialization.Serializable

sealed class Pages {
    @Serializable
    object LoginScreen

    @Serializable
    data class SettingsScreen(val email: String)
}
