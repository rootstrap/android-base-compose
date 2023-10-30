package com.rootstrap.tv.player.controls

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VideoPlayerControlsState(
    val hideSeconds: Int
) {
    var isDisplayed by mutableStateOf(false)
    private val countDownTimer = MutableStateFlow(value = hideSeconds)

    init {
        MainScope().launch {
            countDownTimer.collectLatest { time ->
                if (time > 0) {
                    isDisplayed = true
                    delay(1000)
                    countDownTimer.emit(countDownTimer.value - 1)
                } else {
                    isDisplayed = false
                }
            }
        }
    }

    suspend fun showControls(seconds: Int = hideSeconds) {
        countDownTimer.emit(seconds)
    }
}

@Composable
fun rememberVideoPlayerState(hideSeconds: Int = 2) =
    remember { VideoPlayerControlsState(hideSeconds = hideSeconds) }
