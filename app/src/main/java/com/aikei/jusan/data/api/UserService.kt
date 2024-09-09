package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getUsers(): List<User>
}
