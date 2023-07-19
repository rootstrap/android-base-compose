package com.rootstrap.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.navigation.AppNavigation
import com.rootstrap.tv.theme.ComposeTVTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            App(navController)
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    ComposeTVTheme {
        AppNavigation(navController)
    }
}

@Composable
@Preview
fun AppPreview() {
    TvComposablePreview { App(rememberNavController()) }
}
