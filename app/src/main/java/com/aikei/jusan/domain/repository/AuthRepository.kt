package com.aikei.jusan.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult?
    suspend fun register(email: String, password: String): AuthResult?
    fun logout()
    fun getCurrentUser(): String?
}
