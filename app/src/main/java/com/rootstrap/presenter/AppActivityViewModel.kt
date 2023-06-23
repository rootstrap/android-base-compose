package com.rootstrap.presenter

import androidx.lifecycle.ViewModel
import com.rootstrap.data.utils.ErrorNotifier

class AppActivityViewModel(
    private val errorNotifier: ErrorNotifier
) : ViewModel() {

    val errorNotification = errorNotifier.errorFlow

    fun clearErrorNotification() {
        errorNotifier.notify(null)
    }

}
