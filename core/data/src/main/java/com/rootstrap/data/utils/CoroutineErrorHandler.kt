package com.rootstrap.data.utils

import android.util.Log
import com.rootstrap.domain.errors.ErrorHandler
import com.rootstrap.domain.errors.ErrorNotification
import com.rootstrap.domain.errors.ErrorNotifier
import com.rootstrap.domain.errors.ErrorType
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * CoroutineErrorHandler is a class that handles uncaught exceptions in coroutines.
 * Implements [CoroutineExceptionHandler], [ErrorHandler], [AbstractCoroutineContextElement].
 * For more information of how to use it go to [ErrorHandler].
 */
class CoroutineErrorHandler(
    private val errorNotifier: ErrorNotifier
) : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler,
    ErrorHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val errorNotification = when (exception) {
            // add your custom exceptions here
            is HttpException -> ErrorNotification(
                ErrorType.HttpException
            )
            is UnknownHostException -> ErrorNotification(
                ErrorType.NetworkError
            )
            else -> ErrorNotification(
                ErrorType.UnknownError(exception)
            )
        }

        Log.e("ErrorHandler", "Error happened: ${exception.message} ")

        errorNotifier.notify(errorNotification)
    }
}
