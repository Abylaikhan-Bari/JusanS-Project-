package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.api.RetrofitInstance
import com.aikei.jusan.data.model.Address
import com.aikei.jusan.data.model.Company
import com.aikei.jusan.data.model.Geo
import com.aikei.jusan.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor() {

    private val userService = RetrofitInstance.user_api

    fun getUsers(): Flow<List<User>> = flow {
        try {
            val users = userService.getUsers()
            emit(users)
        } catch (e: Exception) {
            // Handle exceptions, e.g., network errors
            emit(emptyList())
        }
    }

    fun getCurrentUser(): Flow<User> = flow {
        try {
            val users = userService.getUsers()
            users.firstOrNull()?.let { emit(it) } // Simulate fetching the first user
        } catch (e: Exception) {
            // Handle exceptions, e.g., network errors
            emit(User(id = -1, name = "Unknown", username = "unknown", email = "unknown", address = Address("", "", "", "", Geo("", "")), phone = "unknown", website = "unknown", company = Company("", "", "")))
        }
    }
}
