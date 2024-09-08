package com.aikei.jusan.presentation.ui.screens

import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.presentation.ui.components.AlbumListItem
import com.aikei.jusan.domain.viewmodel.AlbumsViewModel

@Composable
fun AlbumsPage(viewModel: AlbumsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

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
                        AlbumListItem(album = album)
                    }
                }
            }
        }
    }
}
