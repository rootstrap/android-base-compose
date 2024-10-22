package com.rootstrap.data.network

import com.rootstrap.data.store.PreferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val preferencesDataStore: PreferencesDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = runBlocking {
            preferencesDataStore.apiToken.firstOrNull()
        }
        if (!token.isNullOrEmpty()) {
            val requestBuilder = request
                .newBuilder()
                .addHeader(AUTH_HTTP_HEADER, "Bearer $token")

            request = requestBuilder.build()
        }

        return chain.proceed(request)
    }
    companion object {
        private const val AUTH_HTTP_HEADER = "Authorization"
    }
}
