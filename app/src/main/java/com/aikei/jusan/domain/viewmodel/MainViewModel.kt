package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _currentPageTitle = MutableStateFlow("Posts")
    val currentPageTitle: StateFlow<String> = _currentPageTitle.asStateFlow()

    fun updateCurrentPageTitle(title: String) {
        viewModelScope.launch {
            _currentPageTitle.value = title
        }
    }
}
