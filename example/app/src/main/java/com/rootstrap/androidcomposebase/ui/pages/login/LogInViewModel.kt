package com.rootstrap.androidcomposebase.ui.pages.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rootstrap.androidcomposebase.ui.base.BaseViewModel
import com.rootstrap.domain.errors.ErrorHandler
import com.rootstrap.flowforms.core.common.StatusCodes
import com.rootstrap.flowforms.core.dsl.flowForm
import com.rootstrap.flowforms.core.field.FieldStatus
import com.rootstrap.flowforms.core.validation.BasicEmailFormat
import com.rootstrap.flowforms.core.validation.MatchRegex
import com.rootstrap.flowforms.core.validation.MinLength
import com.rootstrap.flowforms.core.validation.Required
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LogInViewModel(
    private val errorHandler: ErrorHandler
) : BaseViewModel<LogInUiState, LoginEvent>(LogInUiState()) {

    private val form = flowForm {
        field(
            id = EMAIL,
            Required { uiState.email },
            BasicEmailFormat { uiState.email }
        )
        field(
            id = PASSWORD,
            Required { uiState.password },
            MinLength(PASSWORD_MIN_LENGTH) { uiState.password },
            MatchRegex(PASSWORD_REGEX) { uiState.password }
        )
    }

    init {
        form.status.distinctUntilChanged().onEach { formStatus ->
            updateUiState { it.copy(isFormValid = formStatus.code == StatusCodes.CORRECT) }
        }.launchIn(viewModelScope)

        form.fieldStatusFlows.onEach { statusFlow ->
            statusFlow.distinctUntilChanged().onEach(::onFieldStatusChange).launchIn(viewModelScope)
        }
    }

    private fun onFieldStatusChange(fieldStatus: FieldStatus) {
        updateUiState {
            when (fieldStatus.fieldId) {
                EMAIL -> it.copy(emailErrorCode = fieldStatus.code)
                else -> it.copy(passwordErrorCode = fieldStatus.code)
            }
        }
    }

    fun onEmailChanged(email: String) {
        updateUiState { it.copy(email = email) }
        viewModelScope.launch {
            val isEmailValid = form.validateOnValueChange(EMAIL)
            updateUiState {
                it.copy(
                    isEmailValid = isEmailValid,
                    isFormValid = isEmailValid && it.isPasswordValid
                )
            }
        }
    }

    fun onPasswordChanged(password: String) {
        updateUiState { it.copy(password = password) }
        viewModelScope.launch {
            val isPasswordValid = form.validateOnValueChange(PASSWORD)
            updateUiState {
                it.copy(
                    isFormValid = isPasswordValid && it.isEmailValid,
                    isPasswordValid = isPasswordValid
                )
            }
        }
    }

    fun onLogin() {
        viewModelScope.launch(errorHandler) {
            // TODO show loading
            Log.i("LogInViewModel", "Login button clicked")
            // TODO: Do log in request
        }.invokeOnCompletion {
            // TODO hide loading
            pushEvent(LoginEvent.LoginSuccess(uiState.email))
        }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private val PASSWORD_REGEX =
            Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!]).*$")
        const val PASSWORD_MIN_LENGTH = 6
    }
}
