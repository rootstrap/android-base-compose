package com.rootstrap.tv.utils

import com.rootstrap.tv.utils.extensions.padStartWith0

object VideoControls {
    fun getContentDurationString(contentDurationInMillis: Long): String {
        val contentDurationMinutes =
            (contentDurationInMillis / 1000 / 60).coerceAtLeast(minimumValue = 0)
        val contentDurationSeconds =
            (contentDurationInMillis / 1000 % 60).coerceAtLeast(minimumValue = 0)
        val contentDurationMinutesStr =
            if (contentDurationMinutes < 10) {
                contentDurationMinutes.padStartWith0()
            } else {
                contentDurationMinutes.toString()
            }
        val contentDurationSecondsStr =
            if (contentDurationSeconds < 10) {
                contentDurationSeconds.padStartWith0()
            } else {
                contentDurationSeconds.toString()
            }
        return "$contentDurationMinutesStr:$contentDurationSecondsStr"
    }

    fun getContentProgressString(contentProgressInMillis: Long): String {
        val contentProgressMinutes = (contentProgressInMillis / 1000) / 60
        val contentProgressSeconds = (contentProgressInMillis / 1000) % 60
        val contentProgressMinutesStr =
            if (contentProgressMinutes < 10) {
                contentProgressMinutes.padStartWith0()
            } else {
                contentProgressMinutes.toString()
            }
        val contentProgressSecondsStr =
            if (contentProgressSeconds < 10) {
                contentProgressSeconds.padStartWith0()
            } else {
                contentProgressSeconds.toString()
            }
        return "$contentProgressMinutesStr:$contentProgressSecondsStr"
    }
}
