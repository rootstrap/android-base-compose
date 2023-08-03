package com.rootstrap.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rootstrap.data.BuildConfig
import com.rootstrap.data.utils.Constants.ApplicationJsonMediaType
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

class ApiProvider(
    val json: Json,
    val httpClientProvider: HttpClientProvider
) {

    val myApi: MyApi by lazy {
        createRetrofitConfig(baseUrl = BuildConfig.API_BASE_URL)
            .create(MyApi::class.java)
    }

    private fun createRetrofitConfig(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClientProvider.cachedOkHttpClient)
        .addConverterFactory(json.asConverterFactory(ApplicationJsonMediaType))
        .build()
}
