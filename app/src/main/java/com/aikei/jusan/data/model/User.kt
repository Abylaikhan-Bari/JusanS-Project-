package com.aikei.jusan.data.model

data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val website: String,
    val profilePictureUrl: String,
    val company: String? = null, // Optional, in case not all users have this info
    val address: String? = null, // Optional, in case not all users have this info
    val todos: List<String> = emptyList() // List of ToDos
)
