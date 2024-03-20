package com.rootstrap.bottomnavapp.ui.model

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
import com.rootstrap.bottomnavapp.ui.theme.compactDimensions
import com.rootstrap.bottomnavapp.ui.theme.defaultDimensions
import com.rootstrap.bottomnavapp.ui.theme.expandedDimensions

enum class WindowType(
    val maxHeight: Dp,
    val maxWidth: Dp,
    val dimensions: Dimensions
) {
    COMPACT(600.dp, 480.dp, compactDimensions),
    MEDIUM(840.dp, 900.dp, defaultDimensions),
    EXPANDED(Dp.Infinity, Dp.Infinity, expandedDimensions);

    companion object {
        @Composable
        fun getDimensions(context: Context): Dimensions {
            val configuration = LocalConfiguration.current
            val windowMetrics = remember(configuration) {
                WindowMetricsCalculator.getOrCreate()
                    .computeCurrentWindowMetrics(context)
            }
            val windowDpSize = with(LocalDensity.current) {
                windowMetrics.bounds.toComposeRect().size.toDpSize()
            }

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
