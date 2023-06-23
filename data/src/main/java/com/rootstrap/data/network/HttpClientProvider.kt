package com.rootstrap.data.network

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import java.util.concurrent.TimeUnit

interface HttpClientProvider {
    val cachedOkHttpClient: OkHttpClient
}

class HttpClientProviderImpl( // TODO set as singleton using Koin
    application: Application, // TODO inject using Koin
    private val isDebugMode: Boolean, // TODO find where to obtain this.
    private val authInterceptor: AuthInterceptor, // TODO inject using Koin
) : HttpClientProvider {

    private val cache = Cache(application.cacheDir, 50L * 1024 * 1024)

    override val cachedOkHttpClient: OkHttpClient get() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (isDebugMode) BODY else NONE
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .readTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    companion object {
        private const val HTTP_TIMEOUT_IN_SECONDS = 60L
    }
}