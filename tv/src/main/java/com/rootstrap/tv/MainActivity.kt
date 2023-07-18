package com.rootstrap.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.Text
import com.rootstrap.tv.theme.ComposeTVTheme
import com.rootstrap.tv.common.TvComposablePreview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    ComposeTVTheme {
        HelloWorld()
    }
}

@Composable
fun HelloWorld() {
    Text("Hello world")
}

@Composable
@Preview
fun AppPreview(){
    TvComposablePreview { App() }
}
