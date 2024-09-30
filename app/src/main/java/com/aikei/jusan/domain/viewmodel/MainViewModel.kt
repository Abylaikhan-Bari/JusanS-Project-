package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // Mutable internal state for page title
    private val _currentPageTitle = MutableStateFlow("Posts")
    val currentPageTitle: StateFlow<String> = _currentPageTitle.asStateFlow()

    // Mutable internal state for current user
    private val _currentUsername = MutableStateFlow("")
    val currentUsername: StateFlow<String> = _currentUsername.asStateFlow()

    init {
        // Fetch the current user and set the username
        viewModelScope.launch {
            userRepository.getCurrentUser().collect { user ->
                _currentUsername.value = user.username // Updating username with repository value
            }
        }
    }

    // Function to reset the app state on sign-out
    fun resetState() {
        _currentPageTitle.value = "App" // Reset the page title
        _currentUsername.value = ""     // Reset the username
    }

    // Function to update the current page title
    fun updateCurrentPageTitle(title: String) {
        _currentPageTitle.value = title // Update page title
    }
}
