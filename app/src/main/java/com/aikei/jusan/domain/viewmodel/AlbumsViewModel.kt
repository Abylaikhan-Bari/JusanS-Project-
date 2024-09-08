package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Album
import com.aikei.jusan.data.repository.AlbumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AlbumUiState(
    val albums: List<Album> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class AlbumsViewModel : ViewModel() {
    private val albumRepository = AlbumRepository()

    private val _uiState = MutableStateFlow(AlbumUiState(isLoading = true))
    val uiState: StateFlow<AlbumUiState> = _uiState.asStateFlow()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                _uiState.value = AlbumUiState(
                    albums = albumRepository.getAlbums(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = AlbumUiState(
                    albums = emptyList(),
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
