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
    onLoginSuccess: () -> Unit,    // Callback when login succeeds
    isLoginPage: Boolean,          // Boolean to toggle between login and register
    onRegisterSuccess: () -> Unit  // Callback when registration succeeds
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

        // Dynamic button for Login or Register
        Button(
            onClick = {
                if (isLoginPage) {
                    authViewModel.login(email, password)
                    if (authViewModel.isLoginSuccessful) {
                        onLoginSuccess()  // Navigate to main screen after login success
                    } else {
                        errorMessage = authViewModel.errorMessage // Show login error
                    }
                } else {
                    authViewModel.register(email, password)
                    if (authViewModel.errorMessage.isEmpty()) {
                        onRegisterSuccess()  // Switch to login page after successful registration
                    } else {
                        errorMessage = authViewModel.errorMessage // Show registration error
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
    }
}
