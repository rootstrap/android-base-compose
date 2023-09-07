package com.rootstrap.yourAppName.ui.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.rootstrap.yourAppName.ui.theme.Dimensions
import com.rootstrap.yourAppName.ui.theme.defaultDimensions

enum class WindowType(
    val maxHeight: Dp,
    val maxWidth: Dp,
    val dimensions: Dimensions
) {
    COMPACT(600.dp, 480.dp, defaultDimensions),
    MEDIUM(840.dp, 900.dp, defaultDimensions),
    EXPANDED(Dp.Infinity, Dp.Infinity, defaultDimensions);

    companion object {
        fun getDimensions(windowDpSize: DpSize): Dimensions {
            val windowType = when {
                windowDpSize.width < COMPACT.maxWidth -> COMPACT
                windowDpSize.height < COMPACT.maxHeight -> COMPACT
                windowDpSize.width < MEDIUM.maxWidth -> MEDIUM
                windowDpSize.height < MEDIUM.maxHeight -> MEDIUM
                else -> EXPANDED
            }
            return windowType.dimensions
        }
    }
}
