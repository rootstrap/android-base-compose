package com.rootstrap.androidcomposebase.ui.pages.login

import android.util.Patterns
import com.rootstrap.example.data.PatternsUtil
import com.rootstrap.androidcomposebase.ui.base.BaseViewModel
import java.util.regex.Pattern

class LogInViewModel : BaseViewModel<LoginUiState, Any>(LoginUiState()) {

    fun onEmailChanged(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.toRegex().matches(email) || email.isEmpty()
        updateUiState { it.copy(email = email, showEmailError = !isEmailValid) }
    }

    fun onPasswordChanged(password: String) {
        val isPasswordValid = Pattern.compile(PatternsUtil.PASSWORD_REGEX).matcher(password)
            .matches() || password.isEmpty()
        updateUiState { it.copy(password = password, showPasswordError = !isPasswordValid) }
    }

    fun onLogInButtonClicked() {
        // TODO: Do log in request
    }
}
