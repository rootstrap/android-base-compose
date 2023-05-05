package com.rootstrap.androidcomposebase.ui.pages.login

data class LoginUiState(
    val email: String = "",
    val showEmailError: Boolean = false,
    val password: String = "",
    val showPasswordError: Boolean = false,
) {

    val isButtonEnabled: Boolean
        get() {
            return email.isNotEmpty() && !showEmailError && password.isNotEmpty() && !showPasswordError
        }
}
