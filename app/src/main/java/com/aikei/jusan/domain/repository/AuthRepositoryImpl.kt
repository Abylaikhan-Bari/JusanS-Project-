package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.firebase.FirebaseAuthService
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: FirebaseAuthService): AuthRepository {
    override suspend fun login(email: String, password: String): AuthResult? {
        return authService.signInWithEmailPassword(email, password)
    }

    override suspend fun register(email: String, password: String): AuthResult? {
        return authService.registerWithEmailPassword(email, password)
    }

    override fun logout() {
        authService.signOut()
    }

    override fun getCurrentUser(): String? {
        return authService.getCurrentUser()?.email
    }
}
