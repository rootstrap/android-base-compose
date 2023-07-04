package com.rootstrap.presenter

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rootstrap.presenter.pages.main.MainPage
import com.rootstrap.presenter.theme.AppTheme

class AppActivity : ComponentActivity() {

    private lateinit var viewModel: AppActivityViewModel // TODO inject using Koin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val errorNotification by viewModel.errorNotification.collectAsStateWithLifecycle()

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainPage()
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
