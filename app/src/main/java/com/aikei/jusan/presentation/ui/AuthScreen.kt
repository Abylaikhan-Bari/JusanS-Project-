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
    onLoginSuccess: () -> Unit,  // Regular callback after successful login
    authViewModel: AuthViewModel = hiltViewModel(),  // Injecting AuthViewModel using Hilt
    navController: NavHostController,
    snackbarHostState: SnackbarHostState  // SnackbarHostState for showing feedback
) {
    var isLoginPage by remember { mutableStateOf(true) }
    var showLoginSnackbar by remember { mutableStateOf(false) }
    var showRegisterSnackbar by remember { mutableStateOf(false) }

    // Trigger showing snackbar after login
    if (showLoginSnackbar) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar("Login successful!")
            showLoginSnackbar = false // Reset after showing
        }
    }

    // Trigger showing snackbar after registration
    if (showRegisterSnackbar) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar("Registration successful! Please log in.")
            showRegisterSnackbar = false // Reset after showing
        }
    }

    // Define the structure of the AuthScreen with toggling between login and register
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,  // Adjust vertical space
        horizontalAlignment = Alignment.CenterHorizontally  // Center content horizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Use one composable for both login and registration
        LoginRegisterPage(
            authViewModel = authViewModel,
            onLoginSuccess = {
                onLoginSuccess()  // Trigger successful login callback
                showLoginSnackbar = true  // Set flag to show login snackbar
            },
            isLoginPage = isLoginPage,
            onRegisterSuccess = {
                isLoginPage = true  // Switch to login page after successful registration
                showRegisterSnackbar = true  // Set flag to show registration snackbar
            },
            toggleLoginRegister = { isLoginPage = !isLoginPage }
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}
