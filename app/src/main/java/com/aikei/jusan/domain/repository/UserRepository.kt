package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor() {

    fun getUsers(): Flow<List<User>> = flow {
        emit(
            listOf(
                User(
                    id = "1",
                    username = "johndoe",
                    fullName = "Johny Doe",
                    email = "johndoe@mail.com",
                    phone = "+77777777701",
                    website = "www.johndoe.com",
                    profilePictureUrl = "https://example.com/user1.jpg",
                    companyName = "Doe Enterprises",
                    businessServices = "Consulting, Product Development",
                    street = "123 Main St",
                    suite = "Apt 4B",
                    city = "Springfield",
                    zipcode = "12345",
                    todos = listOf("Complete project report", "Buy groceries", "Schedule meeting")
                ),
                User(
                    id = "2",
                    username = "janesmith",
                    fullName = "Jane Smith",
                    email = "janesmith@mail.com",
                    phone = "+77777777702",
                    website = "www.janesmith.com",
                    profilePictureUrl = "https://example.com/user2.jpg",
                    companyName = "Smith Consulting",
                    businessServices = "Market Research, Financial Analysis",
                    street = "456 Elm St",
                    suite = "Suite 12",
                    city = "Springfield",
                    zipcode = "67890",
                    todos = listOf("Prepare presentation", "Call client", "Finish article")
                ),
                User(
                    id = "3",
                    username = "alicejohnson",
                    fullName = "Alice Johnson",
                    email = "alicejohnson@mail.com",
                    phone = "+77777777703",
                    website = "www.alicejohnson.com",
                    profilePictureUrl = "https://example.com/user3.jpg",
                    companyName = null, // Optional field
                    businessServices = null, // Optional field
                    street = null, // Optional field
                    suite = null, // Optional field
                    city = null, // Optional field
                    zipcode = null, // Optional field
                    todos = listOf("Submit application", "Update resume", "Attend workshop")
                )
            )
        )
    }

    fun getCurrentUser(): Flow<User> = flow {
        emit(getUsers().first().first()) // Simulate fetching the first user
    }
}
