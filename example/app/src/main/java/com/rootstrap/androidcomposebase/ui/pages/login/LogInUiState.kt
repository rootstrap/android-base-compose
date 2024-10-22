package com.rootstrap.androidcomposebase.ui.pages.login

import com.rootstrap.androidcomposebase.ui.base.UiState

data class LogInUiState(
    val email: String = "",
    val emailErrorCode: String? = null,
    val password: String = "",
    val passwordErrorCode: String? = null,
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isFormValid: Boolean = false
) : UiState

sealed class LoginEvent {
    data class LoginSuccess(val email: String) : LoginEvent()
}
