package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.aikei.jusan.data.model.User
import com.aikei.jusan.data.repository.UserRepository

class UsersViewModel : ViewModel() {
    private val userRepository = UserRepository()
    val users: List<User> = userRepository.getUsers()
}
