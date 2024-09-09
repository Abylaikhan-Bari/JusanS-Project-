package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.Todo
import retrofit2.http.GET
import retrofit2.http.Query

interface ToDoApiService {
    @GET("todos")
    suspend fun getUserTodos(@Query("userId") userId: Int): List<Todo>
}
