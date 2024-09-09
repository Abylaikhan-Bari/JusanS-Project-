package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.api.ToDoApiService
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class ToDoRepository @Inject constructor(
    private val toDoApiService: ToDoApiService
) {
    fun getUserTodos(userId: Int) = flow {
        val todos = toDoApiService.getUserTodos(userId)
        emit(todos)
    }
}
