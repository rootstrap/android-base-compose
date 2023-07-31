package com.rootstrap.tv.player

import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import com.rootstrap.tv.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VideoPlayerViewModel : ViewModel() {

    private val uiState: PlayerUiState =
        PlayerUiState()
    private var _uiStateFlow: MutableStateFlow<PlayerUiState> =
        MutableStateFlow(
            uiState
        )
    val uiStateFlow: StateFlow<PlayerUiState> = _uiStateFlow

    // TODO: Fetch video url from API and pass it to the player
    fun onPlayerLoaded(player: Player, movieId: String) {
        _uiStateFlow.update {
            uiState.copy(player = player, movieUrl = Constants.SampleVideoUrl)
        }
    }
}

data class PlayerUiState(val player: Player? = null, val movieUrl: String = "")
