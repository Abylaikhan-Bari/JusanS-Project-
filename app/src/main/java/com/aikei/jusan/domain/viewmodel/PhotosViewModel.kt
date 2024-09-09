package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Photo
import com.aikei.jusan.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PhotoUiState(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState: StateFlow<PhotoUiState> = _uiState

    fun fetchPhotos(albumId: Int) {
        viewModelScope.launch {
            _uiState.value = PhotoUiState(isLoading = true)
            try {
                val photos = photoRepository.getPhotosByAlbumId(albumId)
                _uiState.value = PhotoUiState(photos = photos, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = PhotoUiState(error = e.message, isLoading = false)
            }
        }
    }

    fun fetchAllPhotos() {
        viewModelScope.launch {
            _uiState.value = PhotoUiState(isLoading = true)
            try {
                val allPhotos = photoRepository.getAllPhotos()  // Use getAllPhotos method
                _uiState.value = PhotoUiState(photos = allPhotos, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = PhotoUiState(error = e.message, isLoading = false)
            }
        }
    }

}
