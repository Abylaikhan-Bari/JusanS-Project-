package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.model.User
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun getUsers(): List<User> {
        // Simulate data fetching, replace with actual data source logic
        return listOf(
            User("1", "User1", "John Doe", "user1@mail.com", "+77777777701", "www.user1.com", "https://example.com/user1.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("2", "User2", "Jane Smith", "user2@mail.com", "+77777777702", "www.user2.com", "https://example.com/user2.jpg"),
            User("3", "User3", "Alice Johnson", "user3@mail.com", "+77777777703", "www.user3.com", "https://example.com/user3.jpg")
        )
    }
}
