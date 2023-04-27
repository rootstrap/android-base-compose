package model

sealed class Resource {
    data class Success<T>(val data: T? = null) : Resource()
    object Loading : Resource()
    object Idle : Resource()
    data class Error(val message: String? = null) : Resource()
}
