package com.rootstrap.androidcomposebase.ui.pages.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import com.rootstrap.app.R

@Composable
fun MainScreen(someId: String? = null) {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(
                    vertical = it.calculateTopPadding(),
                    horizontal = it.calculateStartPadding(LayoutDirection.Ltr)
                )
                .fillMaxSize()
        ) {
            Text(text = stringResource(R.string.main_scree))
        }
    }
}
