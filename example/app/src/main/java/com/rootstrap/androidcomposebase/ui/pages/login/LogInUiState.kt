package com.rootstrap.androidcomposebase.ui.pages.login

import com.rootstrap.androidcomposebase.ui.base.UiState

data class LogInUiState(
    val email: String = "",
    val showEmailError: Boolean = false,
    val password: String = "",
    val showPasswordError: Boolean = false
) : UiState {

    val isButtonEnabled: Boolean
        get() {
            return email.isNotEmpty() && !showEmailError && password.isNotEmpty() && !showPasswordError
        }
}
