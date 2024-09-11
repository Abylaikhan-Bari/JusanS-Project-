package com.aikei.jusan.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.domain.repository.AuthRepository
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
}
