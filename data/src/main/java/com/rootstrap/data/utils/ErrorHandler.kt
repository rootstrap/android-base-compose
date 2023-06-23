package com.rootstrap.data.utils

import android.content.res.Resources
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class ErrorHandler(
    private val resources: Resources, // TODO : Inject androidResources using Koin
    private val errorNotifier: ErrorNotifier // TODO : Inject using Koin
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    private val unknownErrorTitle = "" // resources.getString(R.string.unknown_error_title)
    private val unknownErrorDescription = "" // resources.getString(R.string.unknown_error_description)

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val errorNotification = when (exception) {
            is HttpException -> ErrorNotification(
                title = unknownErrorTitle,
                description = exception.response()?.message() ?: unknownErrorDescription // or parse your default BE error responses
            )

            is UnknownHostException,
            is SocketTimeoutException,
            is SocketException,
            is SSLException -> ErrorNotification(
                title = unknownErrorTitle, // TODO get network error title
                description = unknownErrorDescription // TODO get network error description
            )

            else -> ErrorNotification(
                title = unknownErrorTitle,
                description = unknownErrorDescription
            )
            // add your custom exceptions here
        }

        // TODO : use logger of preference to log the error here. We can discuss on sync / RoundTable

        errorNotifier.notify(errorNotification)
    }

}
