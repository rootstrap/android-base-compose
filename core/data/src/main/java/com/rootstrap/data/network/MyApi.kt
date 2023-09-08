package com.rootstrap.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET

// TODO Replace following classes with yours and use in repositories.
interface MyApi {

    @GET("user")
    suspend fun getUser(): UserResponse // use @Serializable data classes directly, no need to manage Calls nor complex stuff
}

@Serializable
data class UserResponse(
    @SerialName("id") val id: String
)
