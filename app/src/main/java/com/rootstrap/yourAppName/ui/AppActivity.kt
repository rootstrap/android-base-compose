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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.rootstrap.yourAppName.ui.navigation.MainNavHost
import com.rootstrap.yourAppName.ui.theme.AppTheme
import com.rootstrap.yourAppName.ui.theme.LocalNavigation
import com.rootstrap.yourAppName.utils.ErrorMapper
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
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(LocalNavigation provides navController) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainNavHost()
                    }
                }
                errorNotification?.let {
                    val error = ErrorMapper.map(it.errorType, resources)
                    GenericErrorDialog(
                        onDismissRequest = viewModel::clearErrorNotification,
                        title = error.title,
                        description = error.description
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
        description: String
    ) {
        AlertDialog(
            onDismissRequest = onDismissRequest
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
