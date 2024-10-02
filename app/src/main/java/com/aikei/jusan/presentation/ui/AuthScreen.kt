package com.aikei.jusan.presentation.ui

import android.annotation.SuppressLint
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    var isLoginPage by remember { mutableStateOf(true) }

    // Trigger the snackbar when the login or registration result is available
    val loginMessage by remember { authViewModel::loginMessage }
    val registerMessage by remember { authViewModel::registerMessage }

    LaunchedEffect(loginMessage) {
        if (loginMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(loginMessage)
            if (authViewModel.isLoginSuccessful) {
                onLoginSuccess()
                authViewModel.resetAuthMessages() // Reset messages after success
            }
        }
    }

    LaunchedEffect(registerMessage) {
        if (registerMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(registerMessage)
            authViewModel.resetAuthMessages() // Reset messages after success/failure
        }
    }

    Scaffold(
        topBar = { /* No AppBar for the AuthScreen */ },
        bottomBar = { /* No BottomBar for the AuthScreen */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginRegisterPage(
                authViewModel = authViewModel,
                isLoginPage = isLoginPage,
                onLoginSuccess = { authViewModel.loginMessage = "Login successful!" },  // Handle login
                onRegisterSuccess = { authViewModel.registerMessage = "Registration successful!" },  // Handle register
                toggleLoginRegister = { isLoginPage = !isLoginPage }  // Toggle login/register
            )
        }
    }
}