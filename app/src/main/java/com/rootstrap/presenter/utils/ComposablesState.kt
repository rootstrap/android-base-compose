package com.rootstrap.presenter.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Resource

class AsyncDataState<T>(data: T? = null) {
    private val _dataState =
        MutableStateFlow(if (data != null) Resource.Success(data = data) else Resource.Idle)
    val state = _dataState.asStateFlow()

    suspend fun emit(newState: T) {
        _dataState.emit(Resource.Success(newState))
    }

    suspend fun isLoading(isLoading: Boolean) {
        if (isLoading)
            _dataState.emit(Resource.Loading)
        else
            idle()
    }

    suspend fun idle() {
        _dataState.emit(Resource.Idle)
    }

    suspend fun onError(message: String? = null) {
        _dataState.emit(
            Resource.Error(message)
        )
    }

    suspend fun emit(state: Resource) {
        when (state) {
            is Resource.Idle -> idle()
            is Resource.Loading -> isLoading(true)
            is Resource.Error -> onError(state.message)
            is Resource.Success<*> -> (state.data as? T)?.let { emit(it) }
        }
    }
}


/**
 * Use this composable widget to manage UI states like
 * **/
@Composable
fun <T> ComposableDataState(
    dataState: StateFlow<T>,
    content: @Composable ((data: T) -> Unit),
) {
    val state = dataState.collectAsState()
    content.invoke(state.value)
}

/**
 * Use this composable widget to manage async data states
 * **/
@Composable
fun <T> ComposeAsyncState(
    dataState: AsyncDataState<T>,
    isLoading: @Composable (() -> Unit)? = null,
    whenData: @Composable ((data: T?) -> Unit)? = null,
    whenIdle: @Composable (() -> Unit)? = null,
    isError: @Composable ((error: String?) -> Unit)? = null,
) {
    val state = dataState.state.collectAsState()

    when (val currentState = state.value) {
        is Resource.Loading -> isLoading?.invoke() ?: CircularProgressIndicator()
        is Resource.Idle -> whenIdle?.invoke() ?: Box {}
        is Resource.Success<*> -> whenData?.invoke(currentState.data as? T) ?: Box {}
        is Resource.Error -> isError?.invoke(currentState.message) ?: Box {}
    }
}
