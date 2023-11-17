package com.rootstrap.tv.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.MaterialTheme.colorScheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.rootstrap.tv.theme.Dimens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BorderedFocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .indication(interactionSource, LocalIndication.current),
        onClick = onClick,
        interactionSource = interactionSource,
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.2f),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = colorScheme.onSurface,
            focusedContainerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = colorScheme.surface,
            focusedContentColor = colorScheme.surface
        ),
        glow = ClickableSurfaceDefaults.glow(
            focusedGlow = Glow(
                elevation = 5.dp,
                elevationColor = colorScheme.surface.copy(alpha = 0.5f)
            )
        ),
        border = ClickableSurfaceDefaults.border(
            focusedBorder = Border(
                BorderStroke(
                    width = Dimens.homeItemBorder,
                    color = colorScheme.surface
                )
            )
        ),
        shape = ClickableSurfaceDefaults.shape(
            shape = ShapeDefaults.Small
        )
    ) {
        content()
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
private fun BorderedFocusableItemPrev() {
    TvComposablePreview() {
        BorderedFocusableItem(onClick = {}) {
            Text(text = "Preview Text")
        }
    }
}
