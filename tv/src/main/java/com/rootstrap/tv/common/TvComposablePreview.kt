package com.rootstrap.tv.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rootstrap.tv.theme.ComposeTVTheme

@Composable
@Preview(showBackground = true, device = Devices.TV_1080p)
fun TvComposablePreview(content: @Composable () -> Unit = {}) {
    ComposeTVTheme {
        content()
    }
}
