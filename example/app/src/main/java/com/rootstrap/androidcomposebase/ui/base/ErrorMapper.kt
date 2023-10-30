package com.rootstrap.androidcomposebase.ui.base

import android.content.res.Resources
import com.rootstrap.domain.errors.ErrorType
import com.rootstrap.example.app.R

/**
 * Maps an [ErrorType] to an [ErrorEntity] to be displayed in the UI.
 * If you need to handle a new Error add it to ErrorType and emit it from CoroutineErrorHandler.
 *
 * @param errorType The [ErrorType] to map
 * @param resources [Resources] object to retrieve error strings
 * @return An [ErrorEntity] with title and description set based on the [ErrorType]
 */
object ErrorMapper {
    fun map(errorType: ErrorType, resources: Resources) = when (errorType) {
        is ErrorType.NetworkError -> ErrorEntity(
            title = resources.getString(R.string.unknown_network_error_title),
            description = resources.getString(R.string.unknown_network_error_description)
        )

        is ErrorType.Unauthorized -> ErrorEntity(
            title = resources.getString(R.string.unauthorized),
            description = resources.getString(R.string.unauthorized_error_description)
        )

        else -> ErrorEntity(
            title = resources.getString(R.string.unknown_network_error_title),
            description = resources.getString(R.string.unknown_error_description)
        )
    }
}
