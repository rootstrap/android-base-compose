package com.rootstrap.yourAppName.ui.model

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
import com.rootstrap.yourAppName.ui.theme.defaultDimensions

/**
 * [WindowType] class will have three different types of screen resolutions.
 * It is important that, for [COMPACT] and [EXPANDED] types to work correctly,
 * their dimensions objects need to be created first and associated in this class.
 *
 */
enum class WindowType(
    val maxHeight: Dp,
    val maxWidth: Dp,
    val dimensions: Dimensions
) {
    COMPACT(600.dp, 480.dp, defaultDimensions),
    MEDIUM(840.dp, 900.dp, defaultDimensions),
    EXPANDED(Dp.Infinity, Dp.Infinity, defaultDimensions);

    companion object {

        /**
         * @param [Context] of the Activity
         * @return [Dimensions] Depending on screen size, this class will return
         * the associated dimens that best fits it.
         *
         */
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
