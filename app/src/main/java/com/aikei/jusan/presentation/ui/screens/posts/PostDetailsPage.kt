package com.aikei.jusan.presentation.ui.screens.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.data.model.Post
import com.aikei.jusan.domain.viewmodel.PostsViewModel

@Composable
fun PostDetailsPage(postId: String?, viewModel: PostsViewModel = hiltViewModel()) {
    val post = viewModel.getPostById(postId)
    val user = post?.let { viewModel.getUserById(it.userId) }

    if (post != null && user != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Author: ${user.name}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = "Comments section", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        // Handle case where post or user is null
        Text("Post or author not found")
    }
}

