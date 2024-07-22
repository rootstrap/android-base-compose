package com.rootstrap.bottomnavapp.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.rootstrap.bottomnavapp.ui.base.navigation.ScreenRoutes
import com.rootstrap.example.bottomnavapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(key1 = Unit){
        delay(2000)
        navHostController.navigate(ScreenRoutes.BottomBar.route)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painterResource(id = R.drawable.rootstrap_logo),
            contentDescription = stringResource(id = R.string.splash_content_description)
        )
    }
}