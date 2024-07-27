package com.aikei.jusan.data.repository

import com.aikei.jusan.data.model.User

class UserRepository {
    fun getUsers(): List<User> {
        // Simulate data fetching, replace with actual data source logic
        return listOf(
            User("1", "User 1", "user1@example.com", "url_to_profile_picture1"),
            User("2", "User 2", "user2@example.com", "url_to_profile_picture2")
        )
    }
}
