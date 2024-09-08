package com.aikei.jusan.presentation.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.aikei.jusan.presentation.ui.components.AlbumListItem
import com.aikei.jusan.presentation.ui.components.BottomNavigation
import com.aikei.jusan.presentation.ui.components.TopAppBar
import com.aikei.jusan.domain.viewmodel.AlbumsViewModel

@Composable
fun AlbumsPage(viewModel: AlbumsViewModel) {
    // Collect the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Scaffold to provide a consistent layout structure with top and bottom bars
    Scaffold{ padding ->
        // LazyColumn for efficient scrolling through the album list
        LazyColumn(contentPadding = padding) {
            items(uiState.albums) { album ->
                AlbumListItem(album = album)
            }
        }
    }
}
