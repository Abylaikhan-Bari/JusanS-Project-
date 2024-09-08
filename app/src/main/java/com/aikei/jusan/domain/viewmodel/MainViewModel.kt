package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _currentPageTitle = MutableStateFlow("Posts")
    val currentPageTitle: StateFlow<String> = _currentPageTitle.asStateFlow()

    fun updateCurrentPageTitle(title: String) {
        viewModelScope.launch {
            _currentPageTitle.value = title
        }
    }
}
