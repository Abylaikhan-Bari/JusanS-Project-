package com.aikei.jusan.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.aikei.jusan.presentation.ui.AuthScreen
import com.aikei.jusan.presentation.ui.MainScreen
import com.aikei.jusan.presentation.ui.screens.auth.PinPage
import com.aikei.jusan.presentation.ui.theme.JusanTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JusanTheme {
                // Call your Composable logic here
                MainAppContent()
            }
        }
    }
}

@Composable
fun MainAppContent() {
    var isAuthenticated by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser != null) }
    var isPinValidated by remember { mutableStateOf(false) }

    if (!isAuthenticated) {
        // Show login/register screen
        AuthScreen()
    } else {
        // Show PIN screen after successful Firebase login
        if (!isPinValidated) {
            PinPage { pin ->
                if (pin == "1234") { // Replace with actual PIN verification logic
                    isPinValidated = true
                }
            }
        } else {
            // Show the main content once the PIN is validated
            MainScreen()
        }
    }
}
