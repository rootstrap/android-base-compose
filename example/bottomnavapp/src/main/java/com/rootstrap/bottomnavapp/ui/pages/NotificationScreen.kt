package com.rootstrap.bottomnavapp.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.rootstrap.bottomnavapp.ui.base.navigation.BottomBarRoutes
import com.rootstrap.example.bottomnavapp.R

@Composable
fun NotificationScreen(navHostController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.notification_screen_label),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
    BackHandler {
        navHostController.navigate(BottomBarRoutes.HOME.routes) {
            popUpTo(BottomBarRoutes.HOME.routes){
                inclusive = true
            }
        }
    }
}