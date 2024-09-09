package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Todo
import com.aikei.jusan.data.model.User
import com.aikei.jusan.domain.repository.ToDoRepository
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val todoRepository: ToDoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadCurrentUserAndTodos()
    }

    private fun loadCurrentUserAndTodos() {
        viewModelScope.launch {
            userRepository.getCurrentUser()
                .onStart {
                    _uiState.value = UiState(isLoading = true)
                }
                .catch { e ->
                    _uiState.value = UiState(isLoading = false, error = e.message)
                }
                .collect { user ->
                    _uiState.value = UiState(user = user, isLoading = false)
                    fetchUserTodos(user.id)
                }
        }
    }

    private fun fetchUserTodos(userId: Int) {
        viewModelScope.launch {
            todoRepository.getUserTodos(userId)
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
                .catch { e ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
                }
                .collect { todos ->
                    _uiState.value = _uiState.value.copy(todos = todos, isLoading = false)
                }
        }
    }

    data class UiState(
        val user: User? = null,
        val todos: List<Todo> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
