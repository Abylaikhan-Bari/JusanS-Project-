package com.aikei.jusan.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signInWithEmailPassword(email: String, password: String): AuthResult? {
        return auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun registerWithEmailPassword(email: String, password: String): AuthResult? {
        return auth.createUserWithEmailAndPassword(email, password).await()
    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUser() = auth.currentUser
}
