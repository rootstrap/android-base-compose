package com.rootstrap.tv.pages.search.keyboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.rootstrap.tv.pages.search.KeyboardLetterType
import com.rootstrap.tv.theme.Dimens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun KeyboardItem(
    key: Any,
    modifier: Modifier = Modifier,
    keyboardLetterType: KeyboardLetterType,
    onKeyPressed: (String, KeyboardLetterType) -> Unit
) {
    KeyboardItem(
        modifier = modifier.aspectRatio(1f),
        keyboardLetterType = keyboardLetterType,
        onKeyPressed = onKeyPressed,
        key = key.toString()
    ) {
        Text(text = key.toString())
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun KeyboardItem(
    modifier: Modifier = Modifier,
    key: String,
    keyboardLetterType: KeyboardLetterType,
    onKeyPressed: (String, KeyboardLetterType) -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.padding(Dimens.paddingQuarter),
        onClick = { onKeyPressed(key, keyboardLetterType) },
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.small)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}
