package com.rootstrap.data.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ErrorNotifier {
    private val _errorFlow = MutableStateFlow<ErrorNotification?>(null)
    val errorFlow: StateFlow<ErrorNotification?> = _errorFlow.asStateFlow()

    fun notify(errorNotification: ErrorNotification?) {
        _errorFlow.update { errorNotification }
    }

}

data class ErrorNotification(
    val title: String,
    val description: String,
)
