package com.rootstrap.domain.errors

/**
 * Sealed class that represents different error types in the app.
 */
sealed class ErrorType {
    object NetworkError : ErrorType()
    object Unauthorized : ErrorType()
    object HttpException : ErrorType()
    data class UnknownError(val throwable: Throwable) : ErrorType()
}
