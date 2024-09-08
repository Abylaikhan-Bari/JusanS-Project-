package com.aikei.jusan.presentation.ui.screens

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.presentation.ui.components.PostListItem
import com.aikei.jusan.domain.viewmodel.PostsViewModel

@Composable
fun PostsPage(viewModel: PostsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { padding ->
        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize()) // No padding needed
            }
            uiState.error != null -> {
                ErrorMessage(message = uiState.error, modifier = Modifier.fillMaxSize()) // No padding needed
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                ) {
                    items(uiState.posts) { post ->
                        PostListItem(post)
                    }
                }
            }
        }
    }
}


@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(message: String?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message ?: "An error occurred",
            color = MaterialTheme.colorScheme.error
        )
    }
}
