package com.aikei.jusan.presentation.ui.screens.albums

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.presentation.ui.components.album.AlbumListItem
import com.aikei.jusan.domain.viewmodel.AlbumsViewModel
import com.aikei.jusan.domain.viewmodel.PhotosViewModel
import com.aikei.jusan.presentation.ui.screens.posts.ErrorMessage
import com.aikei.jusan.presentation.ui.screens.posts.LoadingIndicator

@Composable
fun AlbumsPage(
    viewModel: AlbumsViewModel = hiltViewModel(),
    photoViewModel: PhotosViewModel = hiltViewModel() // Inject the PhotosViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val photoUiState by photoViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAlbumsAndUsers()
        photoViewModel.fetchAllPhotos() // Fetch all photos once
    }

    Scaffold { padding ->
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize().padding(0.dp))
            }
            uiState.error != null -> {
                ErrorMessage(message = uiState.error, modifier = Modifier.fillMaxSize().padding(0.dp))
            }
            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.albums) { album ->
                        val username = viewModel.getUserById(album.userId)?.name ?: "Unknown User"

                        // Filter the first photo for the current album as the cover
                        val coverPhoto = photoUiState.photos.firstOrNull { it.albumId == album.id }

                        AlbumListItem(album = album, username = username, coverPhoto = coverPhoto)
                    }
                }
            }
        }
    }
}
