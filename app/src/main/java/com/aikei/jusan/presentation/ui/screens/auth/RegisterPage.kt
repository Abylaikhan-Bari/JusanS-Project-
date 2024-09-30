package com.aikei.jusan.presentation.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.domain.viewmodel.AuthViewModel

@Composable
fun RegisterPage(
    authViewModel: AuthViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit
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
                authViewModel.register(email, password)
                errorMessage = authViewModel.errorMessage
                if (errorMessage.isEmpty()) {
                    onRegisterSuccess() // Navigate to the login screen after successful registration
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
