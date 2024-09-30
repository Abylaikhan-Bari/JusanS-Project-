package com.aikei.jusan.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aikei.jusan.domain.viewmodel.AuthViewModel
import com.aikei.jusan.presentation.ui.screens.auth.LoginRegisterPage

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit,  // Callback after successful login
    authViewModel: AuthViewModel = hiltViewModel(),  // Injecting AuthViewModel using Hilt
    navController: NavHostController
) {
    var isLoginPage by remember { mutableStateOf(true) }

    // Define the structure of the AuthScreen with toggling between login and register
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Use one composable for both login and registration
        LoginRegisterPage(
            authViewModel = authViewModel,
            onLoginSuccess = onLoginSuccess,
            isLoginPage = isLoginPage,
            onRegisterSuccess = {
                isLoginPage = true // Switch to login page after registration success
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to toggle between Login and Register page
        TextButton(onClick = { isLoginPage = !isLoginPage }) {
            Text(
                if (isLoginPage) "Don't have an account? Register here"
                else "Already have an account? Login here"
            )
        }
    }
}
