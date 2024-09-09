package com.aikei.jusan.presentation.ui.screens.albums

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.domain.viewmodel.PhotosViewModel
import com.aikei.jusan.presentation.ui.components.album.PhotoItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumPhotosPage(albumId: Int, albumTitle: String, viewModel: PhotosViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var isGridView by remember { mutableStateOf(false) }

    LaunchedEffect(albumId) {
        viewModel.fetchPhotos(albumId)
    }

    Scaffold { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null) {
            Text(
                text = "Error: ${uiState.error}",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Album title at the top
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = albumTitle,
                        style = MaterialTheme.typography.titleLarge
                    )

                    // Toggle button on the right side
                    IconButton(onClick = { isGridView = !isGridView }) {
                        Icon(
                            imageVector = if (isGridView) Icons.Default.List else Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }
                }

                if (isGridView) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(uiState.photos) { photo ->
                            PhotoItem(photo = photo)
                        }
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(0.dp),  // No padding on content
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.photos) { photo ->
                            PhotoItem(photo = photo)
                        }
                    }
                }
            }
        }
    }
}