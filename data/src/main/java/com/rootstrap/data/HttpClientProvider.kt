package com.rootstrap.data

import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface HttpClientProvider {
    val cachedOkHttpClient: OkHttpClient
}

class HttpClientProviderImpl( // TODO set as singleton using Koin
    private val cache: Cache, // TODO inject using Koin
    private val interceptorProvider: InterceptorProvider,
) : HttpClientProvider {

    override val cachedOkHttpClient: OkHttpClient get() {
        return OkHttpClient.Builder()
            .cache(cache)
            .readTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(interceptorProvider.httpLoggingInterceptor)
            //.addNetworkInterceptor(interceptors.authInterceptor)
            .build()
    }

    companion object {
        private const val HTTP_TIMEOUT_IN_SECONDS = 60L
    }
}