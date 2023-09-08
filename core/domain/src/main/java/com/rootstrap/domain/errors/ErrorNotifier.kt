package com.rootstrap.domain.errors

import kotlinx.coroutines.flow.StateFlow

interface ErrorNotifier {

    val errorFlow: StateFlow<ErrorNotification?>

    fun notify(errorNotification: ErrorNotification?)
}
