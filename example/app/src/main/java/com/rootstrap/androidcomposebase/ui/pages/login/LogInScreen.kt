package com.rootstrap.androidcomposebase.ui.pages.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rootstrap.androidcomposebase.ui.common.AppButton
import com.rootstrap.androidcomposebase.ui.common.AppTextField
import com.rootstrap.androidcomposebase.ui.navigation.NavigationAppData
import com.rootstrap.androidcomposebase.ui.navigation.Pages
import com.rootstrap.androidcomposebase.ui.pages.login.LogInViewModel.Companion.PASSWORD_MIN_LENGTH
import com.rootstrap.androidcomposebase.ui.theme.AppTheme
import com.rootstrap.example.app.R
import com.rootstrap.flowforms.core.common.StatusCodes
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogInScreen(
    viewModel: LogInViewModel = koinViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    val event: LoginEvent? by viewModel.eventFlow.collectAsStateWithLifecycle(null)
    val navController = NavigationAppData.navController

    LaunchedEffect(event) {
        when (event) {
            is LoginEvent.LoginSuccess -> {
                (event as? LoginEvent.LoginSuccess)?.let {
                    navController.navigate(Pages.SettingsScreen(it.email))
                }
            }

            else -> Unit
        }
    }

    LogInScreen(
        uiState = uiState,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLoginClicked = viewModel::onLogin
    )
}

@Composable
fun LogInScreen(
    uiState: LogInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Black)
            .padding(horizontal = AppTheme.dimens.paddingDouble)
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
                onValueChange = onEmailChanged,
                label = stringResource(id = R.string.log_in_email_label),
                errorMessage = when (uiState.emailErrorCode) {
                    StatusCodes.REQUIRED_UNSATISFIED -> stringResource(id = R.string.log_in_email_error_required)
                    StatusCodes.BASIC_EMAIL_FORMAT_UNSATISFIED -> stringResource(id = R.string.log_in_email_error_format)
                    else -> null
                }
            )
            AppTextField(
                value = uiState.password,
                onValueChange = onPasswordChanged,
                label = stringResource(id = R.string.log_in_password_label),
                errorMessage = when (uiState.passwordErrorCode) {
                    StatusCodes.REQUIRED_UNSATISFIED -> stringResource(id = R.string.log_in_password_error_required)
                    StatusCodes.MIN_LENGTH_UNSATISFIED -> stringResource(
                        id = R.string.log_in_password_error_min_length,
                        PASSWORD_MIN_LENGTH
                    )

                    StatusCodes.MATCH_REGEX_UNSATISFIED -> stringResource(id = R.string.log_in_password_error_format)
                    else -> null
                },
                isPasswordField = true
            )
        }

        AppButton(
            label = R.string.log_in_button,
            enabled = uiState.isFormValid,
            onClick = onLoginClicked
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LogInScreenPreview() {
    AppTheme {
        LogInScreen(
            uiState = LogInUiState(
                email = "email",
                password = "password",
                isFormValid = true
            ),
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {}
        )
    }
}
