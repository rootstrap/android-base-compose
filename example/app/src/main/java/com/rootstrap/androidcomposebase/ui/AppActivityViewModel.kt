package com.rootstrap.androidcomposebase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootstrap.domain.errors.ErrorNotifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppActivityViewModel(
    private val errorNotifier: ErrorNotifier
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    val errorNotification = errorNotifier.errorFlow

    init {
        viewModelScope.launch {
            // TODO: this delay could be replaced by any API call we might need to initialize data
            delay(3_000L)
            _loading.emit(false)
        }
    }

    fun clearErrorNotification() {
        errorNotifier.notify(null)
    }
}
