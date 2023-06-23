package com.rootstrap.presenter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rootstrap.presenter.pages.main.MainPage
import com.rootstrap.presenter.theme.AppTheme

class AppActivity : ComponentActivity() {

    private lateinit var viewModel: AppActivityViewModel // TODO inject using Koin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val errorNotification by viewModel.errorNotification.collectAsState() // TODO use collectStateAsLifecycle
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainPage()
                }

                errorNotification?.let {
                    // TODO display generic error dialog / UI. Remove Log.e line
                    Log.e("AppActivity", errorNotification.toString())
                    viewModel.clearErrorNotification()
                }
            }
        }
    }
}
