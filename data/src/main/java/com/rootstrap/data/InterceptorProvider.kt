package com.rootstrap.data

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

interface InterceptorProvider {
    val httpLoggingInterceptor: HttpLoggingInterceptor
}

class InterceptorProviderImpl( // TODO set as singleton using Koin.
    val isDebugMode: Boolean, // TODO find where to obtain this.
) : InterceptorProvider {

    override val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (isDebugMode) BODY else NONE
    }

    // TODO .addNetworkInterceptor(AuthInterceptor(prefsStorage))

}
