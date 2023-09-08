package com.rootstrap.data.utils

import com.rootstrap.domain.errors.ErrorNotification
import com.rootstrap.domain.errors.ErrorNotifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FlowErrorNotifier : ErrorNotifier {
    private val _errorFlow = MutableStateFlow<ErrorNotification?>(null)
    override val errorFlow: StateFlow<ErrorNotification?> = _errorFlow.asStateFlow()

    override fun notify(errorNotification: ErrorNotification?) {
        _errorFlow.update { errorNotification }
    }
}
