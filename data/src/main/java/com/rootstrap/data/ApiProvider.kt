package com.rootstrap.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiProvider {
    // we can have more than 1 api declared here, so this is very useful for Testing.
    val myApi: MyApi
}

class ApiProviderImpl( // TODO this should be provided as singleton by Koin
    val json: Json, // TODO provide using Koin
    val httpClientProvider: HttpClientProvider // TODO provide using Koin
) : ApiProvider {


    override val myApi: MyApi by lazy {
        createRetrofitConfig("https://some-api-url.com/v1") // TODO get from build config
            .create(MyApi::class.java)
    }

    private fun createRetrofitConfig(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClientProvider.cachedOkHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

}


// TODO remove following classes

interface MyApi {
    @GET("user")
    suspend fun getUser(): User // use @Serializable data classes directly
}

@Serializable
data class User(
    @SerialName("id") val id: String,
)
