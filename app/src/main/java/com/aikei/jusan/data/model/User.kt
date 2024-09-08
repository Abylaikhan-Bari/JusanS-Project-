package com.aikei.jusan.data.model

data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val website: String,
    val profilePictureUrl: String,
    val companyName: String? = null, // Updated to specify company name
    val businessServices: String? = null, // Additional field for business services
    val street: String? = null, // Street address
    val suite: String? = null, // Suite number or apartment
    val city: String? = null, // City
    val zipcode: String? = null, // Postal code
    val todos: List<String> = emptyList() // List of ToDos
)
