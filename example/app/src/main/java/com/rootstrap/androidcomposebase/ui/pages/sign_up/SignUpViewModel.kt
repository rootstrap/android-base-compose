package com.rootstrap.androidcomposebase.ui.pages.sign_up

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.rootstrap.androidcomposebase.ui.pages.login.LoginUiState
import com.rootstrap.example.data.PatternsUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    private var _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState>
        get() = _uiState

    fun onNameChanged(name: String) {
        val isNameValid =
            Pattern.compile(PatternsUtil.NAME_REGEX).matcher(name).matches() || name.isEmpty()
        _uiState.update { it.copy(name = name, showNameError = !isNameValid) }
    }

    fun onEmailChanged(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.toRegex().matches(email) || email.isEmpty()
        _uiState.update { it.copy(email = email, showEmailError = !isEmailValid) }
    }

    fun onPasswordChanged(password: String) {
        val isPasswordValid = Pattern.compile(PatternsUtil.PASSWORD_REGEX).matcher(password)
            .matches() || password.isEmpty()
        _uiState.update { it.copy(password = password, showPasswordError = !isPasswordValid) }
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        val isConfirmPasswordValid = _uiState.value.password == confirmPassword
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                showConfirmPasswordError = !isConfirmPasswordValid
            )
        }
    }

    fun onSignUpButtonClicked() {
        // TODO: Do log in request
    }
}