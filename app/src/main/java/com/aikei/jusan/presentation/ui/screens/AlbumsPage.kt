package com.aikei.jusan.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.presentation.ui.components.AlbumListItem
import com.aikei.jusan.domain.viewmodel.AlbumsViewModel

@Composable
fun AlbumsPage(viewModel: AlbumsViewModel = hiltViewModel()) {
    // Collect the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Scaffold to provide a consistent layout structure with top and bottom bars
    Scaffold{ padding ->
        when {
            uiState.isLoading -> {
                // Show a loading indicator while data is being fetched
                CircularProgressIndicator(
                    modifier = Modifier.padding(padding).fillMaxSize()
                )
            }
            uiState.error != null -> {
                // Show an error message if there was an issue fetching the data
                Text(
                    text = uiState.error!!,
                    modifier = Modifier.padding(padding),
                    color = MaterialTheme.colorScheme.error
                )
            }
            else -> {
                // Display the list of albums
                LazyColumn(contentPadding = padding) {
                    items(uiState.albums) { album ->
                        AlbumListItem(album = album)
                    }
                }
            }
        }
    }
}
