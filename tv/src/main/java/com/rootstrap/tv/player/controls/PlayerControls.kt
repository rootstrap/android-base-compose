package com.rootstrap.tv.player.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.rootstrap.tv.R
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.theme.Dimens.paddingNormal
import com.rootstrap.tv.theme.Dimens.paddingThreeQuarters
import com.rootstrap.tv.utils.VideoControls

@Composable
fun VideoPlayerControls(
    modifier: Modifier = Modifier,
    state: VideoPlayerControlsState = rememberVideoPlayerState(),
    isPlaying: Boolean,
    onPlayPauseToggle: (Boolean) -> Unit,
    onSeek: (seekProgress: Float) -> Unit,
    contentProgressInMillis: Long,
    contentDurationInMillis: Long
) {
    val focusRequester = remember { FocusRequester() }
    val contentProgress by remember(contentProgressInMillis, contentDurationInMillis) {
        derivedStateOf {
            contentProgressInMillis.toFloat() / contentDurationInMillis
        }
    }

    val contentProgressString by remember(contentProgressInMillis) {
        derivedStateOf {
            VideoControls.getContentProgressString(contentProgressInMillis)
        }
    }

    val contentDurationString by remember(contentDurationInMillis) {
        derivedStateOf {
            VideoControls.getContentDurationString(contentDurationInMillis = contentDurationInMillis)
        }
    }

    LaunchedEffect(state.isDisplayed) {
        if (state.isDisplayed) {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(isPlaying) {
        if (!isPlaying) {
            state.showControls(seconds = Int.MAX_VALUE)
        } else {
            state.showControls()
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = state.isDisplayed,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .padding(
                    horizontal = Dimens.paddingQuadruple,
                    vertical = Dimens.paddingDouble
                )
        ) {
            SecondaryButtons(state, isPlaying)
            PrimaryButtons(
                isPlaying = isPlaying,
                focusRequester = focusRequester,
                contentProgress = contentProgress,
                contentDurationString = contentDurationString,
                state = state,
                contentProgressString = contentProgressString,
                onPlayPauseToggle = onPlayPauseToggle,
                onSeek = onSeek
            )
        }
    }
}

@Composable
private fun PrimaryButtons(
    isPlaying: Boolean,
    focusRequester: FocusRequester,
    contentProgress: Float,
    contentDurationString: String,
    state: VideoPlayerControlsState,
    contentProgressString: String,
    onPlayPauseToggle: (Boolean) -> Unit,
    onSeek: (seekProgress: Float) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        VideoPlayerControlsIcon(
            modifier = Modifier.focusRequester(focusRequester),
            icon = if (!isPlaying) R.drawable.ic_play_arrow else R.drawable.ic_pause,
            onClick = { onPlayPauseToggle(!isPlaying) },
            state = state,
            isPlaying = isPlaying,
            contentDescription = stringResource(R.string.play_pause_content_description)
        )
        VideoPlayerControllerText(text = contentProgressString)
        VideoPlayerControllerIndicator(
            progress = contentProgress,
            onSeek = onSeek,
            state = state
        )
        VideoPlayerControllerText(text = contentDurationString)
    }
}

@Composable
private fun SecondaryButtons(state: VideoPlayerControlsState, isPlaying: Boolean) {
    Row(
        modifier = Modifier.padding(bottom = paddingNormal),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VideoPlayerControlsIcon(
            icon = R.drawable.ic_auto_awesome_motion,
            state = state,
            isPlaying = isPlaying,
            contentDescription = stringResource(R.string.playlist)
        )
        VideoPlayerControlsIcon(
            modifier = Modifier.padding(start = paddingThreeQuarters),
            icon = R.drawable.ic_closed_caption,
            state = state,
            isPlaying = isPlaying,
            contentDescription = stringResource(R.string.subtitles)
        )
        VideoPlayerControlsIcon(
            modifier = Modifier.padding(start = paddingThreeQuarters),
            icon = R.drawable.ic_settings,
            state = state,
            isPlaying = isPlaying,
            contentDescription = stringResource(R.string.settings)
        )
    }
}
