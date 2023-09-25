package com.rootstrap.data.utils

import okhttp3.MediaType.Companion.toMediaType

object Constants {
    const val MegaBytes50: Long = 50 * 1024 * 1024
    val ApplicationJsonMediaType = "application/json".toMediaType()
}
