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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aikei.jusan.presentation.ui.components.PostListItem
import com.aikei.jusan.presentation.ui.components.TopAppBar
import com.aikei.jusan.domain.viewmodel.PostsViewModel
import com.aikei.jusan.data.model.Post

@Composable
fun PostsPage(viewModel: PostsViewModel) {
    val posts = viewModel.posts.value // Access posts.value

    Scaffold { paddingValues ->
        if (viewModel.isLoading) {
            LoadingIndicator()
        } else if (viewModel.error != null) {
            ErrorMessage(viewModel.error)
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(posts) { post ->
                    PostListItem(post)
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator() // Use a loading indicator from the material library
    }
}

@Composable
fun ErrorMessage(message: String?) {
    Text(
        text = message ?: "An error occurred",
        modifier = Modifier.padding(16.dp),
        color = MaterialTheme.colorScheme.error
    )
}

@Preview(showBackground = true)
@Composable
fun PostsPagePreview() {
    val viewModel = PostsViewModel()

    PostsPage(viewModel = viewModel)
}
