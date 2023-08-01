package com.rootstrap.tv.player

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSession.ControllerInfo
import androidx.media3.session.MediaSessionService

class PlaybackService : MediaSessionService() {

    private lateinit var player: Player
    private lateinit var mediaSession: MediaSession

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this)
            .setHandleAudioBecomingNoisy(true)
            .build()
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onDestroy() {
        player.stop()
        player.release()
        mediaSession.release()
        super.onDestroy()
    }
}
