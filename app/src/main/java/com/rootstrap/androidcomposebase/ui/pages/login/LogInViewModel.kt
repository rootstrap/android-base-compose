package com.rootstrap.androidcomposebase.ui.pages.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.rootstrap.androidcomposebase.util.PatternsUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern

class LogInViewModel : ViewModel() {

    private var _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState

    fun onEmailChanged(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.toRegex().matches(email) || email.isEmpty()
        _uiState.update { it.copy(email = email, showEmailError = !isEmailValid) }
    }

    fun onPasswordChanged(password: String) {
        val isPasswordValid = Pattern.compile(PatternsUtil.PASSWORD_REGEX).matcher(password).matches() || password.isEmpty()
        _uiState.update { it.copy(password = password, showPasswordError = !isPasswordValid) }
    }

    fun onLogInButtonClicked() {
        // TODO: Do log in request
    }
}
