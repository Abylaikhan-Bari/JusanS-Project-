package com.aikei.jusan.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aikei.jusan.domain.viewmodel.AuthViewModel
import com.aikei.jusan.presentation.ui.AuthScreen
import com.aikei.jusan.presentation.ui.MainScreen
import com.aikei.jusan.presentation.ui.navigation.NavGraph
import com.aikei.jusan.presentation.ui.screens.auth.PinPage
import com.aikei.jusan.presentation.ui.screens.auth.PinSetupPage
import com.aikei.jusan.presentation.ui.theme.JusanTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JusanTheme {
                // Calling Composable logic
                MainAppContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainAppContent(authViewModel: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var isAuthenticated by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser != null) }
    var isPinSet by remember { mutableStateOf(false) }
    var isPinValidated by remember { mutableStateOf(false) }

    // Snackbar state and message
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf("") }

    // Update authentication status when the user signs in or out
    LaunchedEffect(FirebaseAuth.getInstance().currentUser) {
        isAuthenticated = FirebaseAuth.getInstance().currentUser != null
        if (isAuthenticated) {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            authViewModel.checkIfPinIsSet(uid) { isSet -> isPinSet = isSet }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }  // Use SnackbarHost for feedback
    ) {
        LaunchedEffect(snackbarMessage) {
            if (snackbarMessage.isNotEmpty()) {
                snackbarHostState.showSnackbar(snackbarMessage)
                snackbarMessage = ""  // Reset message after showing snackbar
            }
        }

        if (!isAuthenticated) {
            AuthScreen(
                onLoginSuccess = {
                    isAuthenticated = true
                    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                    authViewModel.checkIfPinIsSet(uid) { isSet -> isPinSet = isSet }
                },
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        } else {
            if (!isPinSet) {
                PinSetupPage(onPinSet = { pin ->
                    authViewModel.savePin(pin)
                    isPinSet = true
                    snackbarMessage = "PIN set successfully!"
                })
            } else if (!isPinValidated) {
                PinPage(onPinEnter = { enteredPin ->
                    authViewModel.validatePin(enteredPin) { isValid ->
                        if (isValid) {
                            isPinValidated = true
                            snackbarMessage = "PIN validated successfully!"
                        } else {
                            snackbarMessage = "Invalid PIN, please try again."
                        }
                    }
                })
            } else {
                MainScreen(
                    onSignOut = {
                        authViewModel.signOut(navController)
                        snackbarMessage = "Signed out successfully."
                    },
                    navController = navController
                )
            }
        }
    }
}
