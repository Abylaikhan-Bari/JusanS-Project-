package com.aikei.jusan.viewmodel

import androidx.lifecycle.ViewModel
import com.aikei.jusan.data.model.User
import com.aikei.jusan.data.repository.UserRepository

class CurrentProfileViewModel : ViewModel() {
    private val userRepository = UserRepository()
    // Simulate fetching current user, replace with actual user data source logic
    val currentUser: User = userRepository.getUsers().first()
}
