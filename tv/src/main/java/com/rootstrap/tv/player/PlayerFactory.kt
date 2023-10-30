package com.rootstrap.tv.player

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.media3.common.Player
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

object PlayerFactory {

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    fun create(
        context: Context,
        player: Player
    ): PlayerView {
        return PlayerView(context).apply {
            hideController()
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

            this.player = player
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
    }
}
