package com.rootstrap.tv.player.controls

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import com.rootstrap.tv.theme.Dimens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VideoPlayerControlsIcon(
    modifier: Modifier = Modifier,
    state: VideoPlayerControlsState,
    isPlaying: Boolean,
    @DrawableRes icon: Int,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused && isPlaying) {
        if (isFocused && isPlaying) {
            state.showControls()
        }
    }

    Surface(
        modifier = modifier.size(Dimens.iconSize),
        onClick = onClick,
        shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ),
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.05f),
        interactionSource = interactionSource
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.paddingHalf),
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}
