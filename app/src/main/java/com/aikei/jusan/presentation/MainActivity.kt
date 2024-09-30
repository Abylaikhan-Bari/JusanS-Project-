package com.aikei.jusan.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
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
    var isPinSet by remember { mutableStateOf(false) }
    var isPinValidated by remember { mutableStateOf(false) }

    // Check authentication status
    LaunchedEffect(key1 = FirebaseAuth.getInstance().currentUser) {
        isAuthenticated = FirebaseAuth.getInstance().currentUser != null
        if (isAuthenticated) {
            // Check if PIN is already set
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            checkIfPinIsSet(uid) { isSet ->
                isPinSet = isSet
            }
        }
    }

    // Render content based on authentication and PIN status
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
            // Show PIN setup page only once (if no PIN has been set)
            PinSetupPage(onPinSet = { pin ->
                savePin(pin)
                isPinSet = true
            })
        } else {
            if (!isPinValidated) {
                // Prompt to enter PIN if it has been set before
                PinPage(onPinEnter = { enteredPin ->
                    validatePin(enteredPin) { isValid ->
                        if (isValid) {
                            isPinValidated = true
                        }
                    }
                })
            } else {
                // Show main content after PIN is validated
                MainScreen(
                    onSignOut = {
                        FirebaseAuth.getInstance().signOut()
                        handleSignOut(navController)
                    },
                    navController = navController
                )
            }
        }
    }
}

fun handleSignOut(navController: NavHostController) {
    // Clear the backstack and navigate to AuthScreen
    navController.navigate(NavGraph.AuthScreen.route) {
        popUpTo(NavGraph.AuthScreen.route) {
            inclusive = true
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
