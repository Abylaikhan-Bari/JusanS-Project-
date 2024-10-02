package com.aikei.jusan.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.aikei.jusan.domain.repository.AuthRepository
import com.aikei.jusan.presentation.ui.navigation.NavGraph
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var errorMessage by mutableStateOf("")
    var isLoginSuccessful by mutableStateOf(false)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.login(email, password)
                isLoginSuccessful = true
            } catch (e: Exception) {
                errorMessage = e.message ?: "Login failed"
                isLoginSuccessful = false
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                authRepository.register(email, password)
                isLoginSuccessful = true
            } catch (e: Exception) {
                errorMessage = e.message ?: "Registration failed"
                isLoginSuccessful = false
            }
        }
    }

    fun signOut(navController: NavHostController) {
        viewModelScope.launch {
            try {
                authRepository.logout()
                isLoginSuccessful = false
                handleSignOut(navController)
            } catch (e: Exception) {
                errorMessage = e.message ?: "Sign out failed"
            }
        }
    }

    // Sign out and clear the backstack
    private fun handleSignOut(navController: NavHostController) {
        navController.navigate(NavGraph.AuthScreen.route) {
            popUpTo(0) { inclusive = true }  // Clears the entire backstack
        }
    }

    // Check if PIN is set in Firestore
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

    // Save the user's PIN to Firestore
    fun savePin(pin: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(uid)
            .set(mapOf("pin" to pin))
            .addOnSuccessListener {
                println("PIN saved successfully.")
            }
            .addOnFailureListener { e ->
                println("Error saving PIN: ${e.message}")
            }
    }

    // Validate the entered PIN
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
                println("Error validating PIN: ${e.message}")
                callback(false)
            }
    }

}
