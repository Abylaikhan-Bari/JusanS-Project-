package com.aikei.jusan.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aikei.jusan.domain.viewmodel.AuthViewModel
import com.aikei.jusan.presentation.ui.screens.auth.LoginPage
import com.aikei.jusan.presentation.ui.screens.auth.RegisterPage

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit,  // Callback after successful login
    authViewModel: AuthViewModel = hiltViewModel(),  // Injecting AuthViewModel using Hilt
    navController: NavHostController
) {
    var showLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // Toggle between Login and Register screen
        if (showLogin) {
            LoginPage(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    onLoginSuccess() // Trigger the success callback to navigate to the main screen
                }
            )
            TextButton(onClick = { showLogin = false }) {
                Text("Don't have an account? Register here")
            }
        } else {
            RegisterPage(
                authViewModel = authViewModel,
                onRegisterSuccess = {
                    showLogin = true // After registration, switch to the login screen
                }
            )
            TextButton(onClick = { showLogin = true }) {
                Text("Already have an account? Login here")
            }
        }
    }
}
