package com.aikei.jusan.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.aikei.jusan.ui.components.AlbumListItem
import com.aikei.jusan.ui.components.BottomNavigation
import com.aikei.jusan.ui.components.TopAppBar
import com.aikei.jusan.viewmodel.AlbumsViewModel

@Composable
fun AlbumsPage(viewModel: AlbumsViewModel) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = { TopAppBar(title = "Albums") },
//        bottomBar = { BottomNavigation() }
//    ) { padding ->
//        LazyColumn(contentPadding = padding) {
//            items(uiState.albums) { album ->
//                AlbumListItem(album = album)
//            }
//        }
//    }
}
