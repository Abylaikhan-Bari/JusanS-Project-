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
class CurrentProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
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
                }
        }
    }

    data class UiState(
        val user: User? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
