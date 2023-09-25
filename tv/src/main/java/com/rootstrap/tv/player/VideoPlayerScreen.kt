package com.rootstrap.tv.player

import android.content.ComponentName
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.rootstrap.tv.player.controls.VideoPlayerControls
import com.rootstrap.tv.player.controls.rememberVideoPlayerState
import com.rootstrap.tv.utils.Constants
import com.rootstrap.tv.utils.extensions.handleDPadKeyEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val HIDE_CONTROLS_TIME = 4

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoPlayerScreen(
    movieId: String,
    viewModel: VideoPlayerViewModel = koinViewModel(),
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiStateFlow.collectAsState()

    LaunchedEffect(key1 = Unit) {
        val sessionToken =
            SessionToken(
                context,
                ComponentName(context, PlaybackService::class.java)
            )

        val controllerFuture =
            MediaController
                .Builder(context, sessionToken)
                .buildAsync()

        controllerFuture.addListener(
            {
                controllerFuture.get()?.apply {
                    viewModel.onPlayerLoaded(this, movieId)
                }
            },
            MoreExecutors.directExecutor()
        )
    }

    BackHandler(onBack = onBackPressed)

    uiState.player?.run {
        Player(
            mediaUrl = uiState.movieUrl,
            player = this,
            context = context,
            modifier = Modifier
        )
    }
}

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun Player(
    mediaUrl: String = Constants.SampleVideoUrl,
    player: Player,
    context: Context,
    modifier: Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val videoPlayerState = rememberVideoPlayerState(hideSeconds = HIDE_CONTROLS_TIME)
    var contentCurrentPosition: Long by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(300)
            contentCurrentPosition = player.currentPosition
        }
    }
    LaunchedEffect(key1 = Unit) {
        setVideoPlayerProperties(player, mediaUrl)
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DisposableEffect(
            AndroidView(
                modifier = Modifier
                    .handleDPadKeyEvents(
                        onEnter = {
                            if (!videoPlayerState.isDisplayed) {
                                coroutineScope.launch {
                                    videoPlayerState.showControls()
                                }
                            }
                        }
                    )
                    .focusable(),
                factory = {
                    PlayerFactory.create(context, player)
                }
            )
        ) {
            onDispose { player.release() }
        }

        VideoPlayerControls(
            modifier = Modifier.align(Alignment.BottomCenter),
            isPlaying = player.isPlaying,
            onPlayPauseToggle = { shouldPlay ->
                if (shouldPlay) {
                    player.play()
                } else {
                    player.pause()
                }
            },
            contentProgressInMillis = contentCurrentPosition,
            contentDurationInMillis = player.duration,
            state = videoPlayerState,
            onSeek = { seekProgress ->
                player.seekTo(player.duration.times(seekProgress).toLong())
            }
        )
    }
}

private fun setVideoPlayerProperties(player: Player, mediaUrl: String) {
    with(player) {
        val mediaMetadata =
            MediaMetadata.Builder()
                .setTitle("Example title")
                .setDisplayTitle("Example display title")
                .build()

        val item = MediaItem.Builder()
            .setUri(mediaUrl)
            .setMediaMetadata(mediaMetadata)
            .build()

        setMediaItem(item)
        playWhenReady = true
        repeatMode = Player.REPEAT_MODE_ONE
        prepare()
        play()
    }
}
