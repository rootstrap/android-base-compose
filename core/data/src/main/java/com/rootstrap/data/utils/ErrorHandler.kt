package com.rootstrap.data.utils

import android.content.res.Resources
import android.util.Log
import com.rootstrap.data.R
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * Use as part of the coroutine's context when launching a new coroutine using ViewModelScope or similar.
 * It will add a default error handling for any uncaught exception in the coroutine.
 * 1. Inject error handler into VM constructor
 * 2. Add it to the coroutine context: viewModelScope.launch(errorHandler) { ... }
 * 3. Handle completion into the coroutine:
     * viewModelScope.launch(errorHandler) {
    *          ...
     *         }.invokeOnCompletion {
     *           .. Do something (like hide loading indicator
     *         }
 * 4. Handle the error in the UI by observing the error notifier:
 *              val errorNotification by viewModel.errorNotification.collectAsStateWithLifecycle()
 *              errorNotification?.let { show error.. }
 */
class ErrorHandler(
    private val resources: Resources,
    private val errorNotifier: ErrorNotifier
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    private val unknownErrorTitle = resources.getString(R.string.unknown_error_title)
    private val unknownErrorDescription = resources.getString(R.string.unknown_error_description)

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val errorNotification = when (exception) {
            is HttpException -> ErrorNotification(
                title = unknownErrorTitle,
                description = exception.response()?.message() ?: unknownErrorDescription, // or parse your default BE error responses
            )

            is UnknownHostException,
            is SocketTimeoutException,
            is SocketException,
            is SSLException -> ErrorNotification(
                title = unknownErrorTitle,
                description = unknownErrorDescription,
            )

            else -> ErrorNotification(
                title = unknownErrorTitle,
                description = unknownErrorDescription,
            )
            // add your custom exceptions here
        }

        // TODO : use logger of preference to log the error here. We can discuss on sync / RoundTable / PR
        Log.e("ErrorHandler", "Error happened: ${errorNotification.title} - ${errorNotification.description}")

        errorNotifier.notify(errorNotification)
    }

}
