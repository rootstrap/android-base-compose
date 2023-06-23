package com.rootstrap.data.network

import com.rootstrap.data.store.PreferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor( // TODO set as singleton in Koin
    private val PreferencesDataStore: PreferencesDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = runBlocking {
            PreferencesDataStore.apiToken.firstOrNull()
        }
        if (!token.isNullOrEmpty()) {
            val requestBuilder = request
                .newBuilder()
                .addHeader(AuthHttpHeader, "Bearer $token")

            request = requestBuilder.build()
        }

        return chain.proceed(request)
    }
    companion object {
        private const val AuthHttpHeader = "Authorization"
    }

}
