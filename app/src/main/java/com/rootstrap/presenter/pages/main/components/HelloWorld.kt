package com.rootstrap.presenter.pages.main.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HelloUser(name: String) {
    Text(text = name)
}

@Preview
@Composable
fun HelloUserPreview() {
    HelloUser("Rootstrap")
}
