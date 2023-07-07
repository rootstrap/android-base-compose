package com.rootstrap.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rootstrap.data.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

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
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

}


// TODO Replace following classes with yours and use in repositories.
interface MyApi {

    @GET("user")
    suspend fun getUser(): UserResponse // use @Serializable data classes directly, no need to manage Calls nor complex stuff
}

@Serializable
data class UserResponse(
    @SerialName("id") val id: String,
)
