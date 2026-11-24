package com.dojo.tasks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.dojo.tasks.R
import com.dojo.tasks.domain.EmailError
import com.dojo.tasks.domain.PasswordError

/** Shared form for both signed-out screens. [mode] picks the title, button and link. No validation here. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthFormScreen(
    mode: AuthScreen,
    errors: AuthErrors,
    onSubmit: (email: String, password: String) -> Unit,
    onSwitchMode: () -> Unit,
) {
    val isSignUp = mode == AuthScreen.SignUp
    var email by rememberSaveable { mutableStateOf("") }
    // Plain remember, not rememberSaveable: the password must never be written into saved instance state.
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(if (isSignUp) R.string.sign_up else R.string.log_in)) }) },
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email_label)) },
                isError = errors.email != null || errors.badCredentials,
                supportingText = errors.email?.let { { Text(stringResource(it.messageRes())) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password_label)) },
                isError = errors.password != null || errors.badCredentials,
                supportingText = passwordMessage(errors)?.let { { Text(stringResource(it)) } },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(onClick = { onSubmit(email, password) }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(if (isSignUp) R.string.sign_up else R.string.log_in))
            }
            TextButton(onClick = onSwitchMode, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(if (isSignUp) R.string.have_account else R.string.create_account))
            }
        }
    }
}

private fun passwordMessage(errors: AuthErrors): Int? = when {
    errors.badCredentials -> R.string.error_bad_credentials
    errors.password == PasswordError.TOO_SHORT -> R.string.error_password_too_short
    else -> null
}

private fun EmailError.messageRes() = when (this) {
    EmailError.REQUIRED -> R.string.error_email_required
    EmailError.INVALID -> R.string.error_email_invalid
    EmailError.TAKEN -> R.string.error_email_taken
}
