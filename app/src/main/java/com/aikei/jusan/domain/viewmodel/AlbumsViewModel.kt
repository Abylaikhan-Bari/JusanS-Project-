package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Album
import com.aikei.jusan.data.model.User
import com.aikei.jusan.domain.repository.AlbumRepository
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AlbumUiState(
    val albums: List<Album> = emptyList(),
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumUiState(isLoading = true))
    val uiState: StateFlow<AlbumUiState> = _uiState.asStateFlow()

    init {
        fetchAlbumsAndUsers()
    }

    fun fetchAlbumsAndUsers() {
        viewModelScope.launch {
            try {
                val albums = albumRepository.getAlbums()
                userRepository.getUsers().collect { usersList ->
                    _uiState.value = _uiState.value.copy(
                        albums = albums,
                        users = usersList,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    albums = emptyList(),
                    users = emptyList(),
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun getUserById(userId: Int): User? {
        return _uiState.value.users.find { it.id == userId }
    }
}
