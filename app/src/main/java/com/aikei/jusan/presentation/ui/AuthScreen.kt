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
import com.aikei.jusan.domain.viewmodel.AuthViewModel
import com.aikei.jusan.presentation.ui.screens.auth.LoginPage
import com.aikei.jusan.presentation.ui.screens.auth.RegisterPage

@Composable
fun AuthScreen() {
    var showLogin by remember { mutableStateOf(true) }

    // Toggle between Login and Register screen
    if (showLogin) {
        LoginPage(onLoginSuccess = {
            // On successful login, switch to main screen
            showLogin = false
        })
    } else {
        RegisterPage(onRegisterSuccess = {
            showLogin = true // After registration, switch to login screen
        })
    }
}
