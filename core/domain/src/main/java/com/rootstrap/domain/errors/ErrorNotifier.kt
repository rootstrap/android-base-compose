package com.rootstrap.domain.errors

import kotlinx.coroutines.flow.StateFlow

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
interface ErrorNotifier {

    val errorFlow: StateFlow<ErrorNotification?>

    fun notify(errorNotification: ErrorNotification?)
}
