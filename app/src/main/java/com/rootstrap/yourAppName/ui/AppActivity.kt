package com.rootstrap.yourAppName.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rootstrap.yourAppName.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppActivity : ComponentActivity() {

    private val viewModel: AppActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.loading.value }
        setContent {
            val errorNotification by viewModel.errorNotification.collectAsStateWithLifecycle()

            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // TODO your UI starts here
                }

                errorNotification?.let {
                    GenericErrorDialog(
                        onDismissRequest = viewModel::clearErrorNotification,
                        title = it.title,
                        description = it.description,
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun GenericErrorDialog(
        onDismissRequest: () -> Unit,
        title: String,
        description: String,
    ) {
        AlertDialog(
            onDismissRequest = onDismissRequest
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
