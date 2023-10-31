package com.rootstrap.yourAppName.ui.pages.error

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Error page example
 * **/
@Composable
fun ErrorPage(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.titleMedium
    )
}
