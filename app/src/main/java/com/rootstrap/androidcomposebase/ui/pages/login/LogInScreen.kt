package com.rootstrap.androidcomposebase.ui.pages.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rootstrap.androidcomposebase.ui.common.AppButton
import com.rootstrap.androidcomposebase.ui.common.AppTextField
import com.rootstrap.androidcomposebase.ui.theme.Padding.huge
import com.rootstrap.app.R

@Composable
fun LogInScreen(
    viewModel: LogInViewModel = LogInViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = huge)
            .fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.rootstrap_logo),
            contentDescription = null,
            tint = Color.White
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            AppTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = stringResource(id = R.string.log_in_email_label),
                showError = uiState.showEmailError,
                errorMessage = stringResource(id = R.string.log_in_email_error)
            )

            AppTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = stringResource(id = R.string.log_in_password_label),
                showError = uiState.showPasswordError,
                errorMessage = stringResource(id = R.string.log_in_password_error),
                isPasswordField = true
            )
        }

        AppButton(
            label = R.string.log_in_button,
            enabled = uiState.isButtonEnabled
        ) {
            viewModel.onLogInButtonClicked()
        }
    }
}
