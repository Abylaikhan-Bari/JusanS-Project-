package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.aikei.jusan.data.model.User
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // StateFlow to manage user list and loading state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        // Simulate data loading
        _uiState.update { it.copy(isLoading = true) }
        try {
            val users = userRepository.getUsers()
            _uiState.update { it.copy(users = users, isLoading = false) }
        } catch (e: Exception) {
            _uiState.update { it.copy(isLoading = false, error = e.message) }
        }
    }

    data class UiState(
        val users: List<User> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
