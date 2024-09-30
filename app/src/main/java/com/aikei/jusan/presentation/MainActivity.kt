package com.aikei.jusan.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
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

@Composable
fun MainAppContent() {
    val navController = rememberNavController()
    var isAuthenticated by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser != null) }
    var isPinSet by remember { mutableStateOf(false) }  // Check if the PIN is set for the user
    var isPinValidated by remember { mutableStateOf(false) }

    if (!isAuthenticated) {
        // Show login/register screen
        AuthScreen(
            onLoginSuccess = {
                isAuthenticated = true
                // After login, check if the user has a PIN set in Firestore
                val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                checkIfPinIsSet(uid) { isSet ->
                    isPinSet = isSet
                }
            },
            navController = navController
        )
    } else {
        if (!isPinSet) {
            // Show PIN setup page if the PIN is not yet set
            PinSetupPage(onPinSet = { pin ->
                savePin(pin)  // Save the PIN to Firebase Firestore or local storage
                isPinSet = true
            })
        } else {
            // Show PIN validation page if the PIN is already set
            if (!isPinValidated) {
                PinPage(onPinEnter = { enteredPin ->
                    validatePin(enteredPin) { isValid ->
                        if (isValid) {
                            isPinValidated = true
                        } else {
                            // Show error message or reset
                            println("PIN is incorrect.")
                        }
                    }
                })

            } else {
                // Show the main content once the PIN is validated
                MainScreen(
                    onSignOut = {
                        FirebaseAuth.getInstance().signOut()
                        isAuthenticated = false
                        isPinValidated = false
                        isPinSet = false
                        navController.navigate(NavGraph.AuthScreen.route) {
                            popUpTo(0)  // Clear backstack
                        }
                    },
                    navController = navController
                )
            }
        }
    }
}

fun checkIfPinIsSet(uid: String, callback: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()

    db.collection("users").document(uid).get()
        .addOnSuccessListener { document ->
            if (document.exists() && document.getString("pin") != null) {
                callback(true)  // PIN exists
            } else {
                callback(false)  // PIN does not exist
            }
        }
        .addOnFailureListener { e ->
            // Handle error
            println("Error checking if PIN is set: ${e.message}")
            callback(false)  // Assume PIN is not set if there's an error
        }
}


fun savePin(pin: String) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val db = FirebaseFirestore.getInstance()

    // Use set instead of update to create the document if it does not exist
    db.collection("users").document(uid)
        .set(mapOf("pin" to pin))
        .addOnSuccessListener {
            // PIN saved successfully
            println("PIN saved successfully.")
        }
        .addOnFailureListener { e ->
            // Handle error in saving PIN
            println("Error saving PIN: ${e.message}")
        }
}

fun validatePin(enteredPin: String, callback: (Boolean) -> Unit) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val db = FirebaseFirestore.getInstance()

    db.collection("users").document(uid).get()
        .addOnSuccessListener { document ->
            val savedPin = document.getString("pin")
            if (savedPin == enteredPin) {
                callback(true)  // PIN is correct
            } else {
                callback(false)  // PIN is incorrect
            }
        }
        .addOnFailureListener { e ->
            // Handle error
            println("Error validating PIN: ${e.message}")
            callback(false)
        }
}

