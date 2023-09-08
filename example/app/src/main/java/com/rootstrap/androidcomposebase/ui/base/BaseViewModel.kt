package com.rootstrap.androidcomposebase.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel<UiStateType : UiState, NavigationEventT : Any>(
    initialUiState: UiStateType
) : ViewModel(), LifecycleObserver {

    private val _uiStateFlow = MutableStateFlow(initialUiState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private val _eventFlow = Channel<NavigationEventT?>()
    val eventFlow = _eventFlow.receiveAsFlow()

    protected val uiState: UiStateType
        get() = _uiStateFlow.value

    protected fun updateUiState(function: (state: UiStateType) -> UiStateType) {
        _uiStateFlow.update(function)
    }

    protected fun pushEvent(event: NavigationEventT?) {
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }
}

interface UiState
