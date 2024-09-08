package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.User
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            userRepository.getUsers()
                .onStart {
                    _uiState.value = UiState(isLoading = true)
                }
                .catch { e ->
                    _uiState.value = UiState(isLoading = false, error = e.message)
                }
                .collect { users ->
                    _uiState.value = UiState(users = users, isLoading = false)
                }
        }
    }

    data class UiState(
        val users: List<User> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
