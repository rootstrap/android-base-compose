package com.rootstrap.androidcomposebase.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rootstrap.androidcomposebase.R
import com.rootstrap.androidcomposebase.ui.common.RSButton
import com.rootstrap.sampleapp.R
import com.rootstrap.sampleapp.ui.common.RSButton
import com.rootstrap.sampleapp.ui.common.RSTextField

@Composable
fun LogInScreen(
    viewModel: LogInViewModel = LogInViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = 20.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.rootstrap_logo),
            contentDescription = null,
            tint = Color.White
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            RSTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = stringResource(id = R.string.log_in_email_label),
                showError = uiState.showEmailError,
                errorMessage = stringResource(id = R.string.log_in_email_error)
            )

            RSTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = stringResource(id = R.string.log_in_password_label),
                showError = uiState.showPasswordError,
                errorMessage = stringResource(id = R.string.log_in_password_error),
                isPasswordField = true
            )
        }

        RSButton(
            label = R.string.log_in_button,
            enabled = uiState.isButtonEnabled
        ) {
            viewModel.onLogInButtonClicked()
        }
    }
}
