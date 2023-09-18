package com.rootstrap.androidcomposebase.ui.pages.sign_up

data class SignUpUiState(
    val name: String = "",
    val showNameError: Boolean = false,
    val email: String = "",
    val showEmailError: Boolean = false,
    val password: String = "",
    val showPasswordError: Boolean = false,
    val confirmPassword: String = "",
    val showConfirmPasswordError: Boolean = false,
) {

    val isButtonEnabled: Boolean
        get() {
            return name.isNotEmpty() && !showNameError && email.isNotEmpty() && !showEmailError
                    && password.isNotEmpty() && !showPasswordError &&
                    confirmPassword.isNotEmpty() && !showConfirmPasswordError
        }
}