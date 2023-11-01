package com.rootstrap.androidcomposebase.ui.pages.login

import com.rootstrap.androidcomposebase.ui.base.UiState

data class LogInUiState(
    val email: String = "",
    val emailErrorCode: String? = null,
    val password: String = "",
    val passwordErrorCode: String? = null,
    val isFormValid: Boolean = false,
) : UiState
