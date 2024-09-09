package com.aikei.jusan.presentation.ui.screens.posts

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.data.model.Comment
import com.aikei.jusan.domain.viewmodel.PostsViewModel

@Composable
fun PostDetailsPage(postId: String?, viewModel: PostsViewModel = hiltViewModel()) {
    val postState by viewModel.uiState.collectAsState()

    // Get post and user based on the postId
    val post = postId?.let { viewModel.getPostById(it) }
    val user = post?.let { viewModel.getUserById(it.userId) }

    // Hold the comments in a local variable
    var comments by remember { mutableStateOf(postState.comments) }
    var showAllComments by remember { mutableStateOf(false) }

    // Fetch comments on post load
    LaunchedEffect(postId) {
        post?.let {
            Log.d("PostDetailsPage", "Fetching comments for postId: ${post.id}")
            viewModel.fetchComments(post.id.toString())
        }
    }

    // If the post is loading, show a smaller loading indicator
    if (postState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp) // Set the size of the indicator to 48.dp
            )
        }
        return
    }

    // If post and user are found, display them
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
                text = "By: ${user.name}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Comments section with toggle to show all
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Comments", style = MaterialTheme.typography.bodyLarge)
                TextButton(onClick = { showAllComments = !showAllComments }) {
                    Text(text = if (showAllComments) "Show Less" else "Show All")
                }
            }

            // Display the comments
            if (comments.isNotEmpty()) {
                Column {
                    if (showAllComments) {
                        comments.forEach { comment ->
                            CommentCard(comment = comment)
                        }
                    } else {
                        comments.take(3).forEach { comment ->
                            CommentCard(comment = comment)
                        }
                    }
                }
            } else {
                Text(text = "No comments available.")
            }
        }
    } else {
        Text("Post or author not found")
    }
}
