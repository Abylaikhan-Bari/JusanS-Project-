package com.aikei.jusan.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    // Toggle between Login and Register screen
    if (showLogin) {
        LoginPage(
            authViewModel = authViewModel,
            onLoginSuccess = {
                onLoginSuccess() // Navigate to the main screen after successful login
            }
        )
    } else {
        RegisterPage(
            authViewModel = authViewModel,
            onRegisterSuccess = {
                showLogin = true // After registration, switch to login screen
            }
        )
    }
}
