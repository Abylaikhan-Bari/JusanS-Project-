package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Todo
import com.aikei.jusan.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            toDoRepository.getUserTodos(userId = 1)
                .onStart { _uiState.value = UiState(isLoading = true) }
                .catch { e -> _uiState.value = UiState(error = e.message) }
                .collect { todos -> _uiState.value = UiState(todos = todos) }
        }
    }

    data class UiState(
        val todos: List<Todo> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}

