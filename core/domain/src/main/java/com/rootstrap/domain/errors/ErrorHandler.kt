package com.rootstrap.domain.errors

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement

/**
 * Extends AbstractCoroutineContextElement and CoroutineExceptionHandler to handle uncaught
 * exceptions in coroutines.
 *
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
abstract class ErrorHandler : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler
