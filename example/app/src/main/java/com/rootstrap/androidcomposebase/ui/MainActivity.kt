package com.rootstrap.androidcomposebase.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.rootstrap.androidcomposebase.ui.base.ErrorMapper
import com.rootstrap.androidcomposebase.ui.navigation.LocalNavigation
import com.rootstrap.androidcomposebase.ui.navigation.MainNavHost
import com.rootstrap.androidcomposebase.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AppActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.loading.value }
        setContent {
            val errorNotification by viewModel.errorNotification.collectAsStateWithLifecycle()

            // TODO Use SharePreference or DataStore
            val isSystemInDarkTheme = isSystemInDarkTheme()
            var isOSDarkTheme by remember { mutableStateOf(isSystemInDarkTheme) }

            AppTheme(isOSDarkTheme = isOSDarkTheme) {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavigation provides navController) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainNavHost(
                            isOSDarkTheme = isOSDarkTheme,
                            onThemeUpdated = { isOSDarkTheme = !isOSDarkTheme }
                        )
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
