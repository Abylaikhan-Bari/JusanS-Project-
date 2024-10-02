package com.aikei.jusan.presentation.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.aikei.jusan.domain.viewmodel.AuthViewModel

@Composable
fun LoginRegisterPage(
    authViewModel: AuthViewModel,  // ViewModel to handle authentication
    isLoginPage: Boolean,          // Boolean to toggle between login and register
    onLoginSuccess: () -> Unit,    // Regular callback when login succeeds
    onRegisterSuccess: () -> Unit, // Regular callback when registration succeeds
    toggleLoginRegister: () -> Unit // Callback to toggle between login and register
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isLoginPage) {
                    authViewModel.login(email, password)
                    if (authViewModel.isLoginSuccessful) {
                        onLoginSuccess()  // Trigger login success callback
                    } else {
                        errorMessage = authViewModel.errorMessage
                    }
                } else {
                    authViewModel.register(email, password)
                    if (authViewModel.isLoginSuccessful) {
                        onRegisterSuccess()  // Trigger registration success callback
                    } else {
                        errorMessage = authViewModel.errorMessage
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLoginPage) "Login" else "Register")
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Toggle button to switch between Login and Register page
        TextButton(onClick = toggleLoginRegister, modifier = Modifier.fillMaxWidth()) {
            Text(
                if (isLoginPage) "Don't have an account? Register here"
                else "Already have an account? Login here"
            )
        }
    }
}
