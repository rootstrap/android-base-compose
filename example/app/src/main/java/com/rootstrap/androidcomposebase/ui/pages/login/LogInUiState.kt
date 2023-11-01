package com.rootstrap.androidcomposebase.ui.pages.login

import com.rootstrap.androidcomposebase.ui.base.UiState

data class LogInUiState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isFormValid: Boolean = false,
) : UiState
