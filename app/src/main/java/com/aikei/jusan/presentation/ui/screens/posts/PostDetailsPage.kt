package com.aikei.jusan.presentation.ui.screens.posts

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.data.model.Comment
import com.aikei.jusan.domain.viewmodel.PostsViewModel

@Composable
fun PostDetailsPage(postId: String?, viewModel: PostsViewModel = hiltViewModel()) {
    val postState by viewModel.uiState.collectAsState()

    // Directly use the comments from the state
    val comments = postState.comments
    var showAllComments by remember { mutableStateOf(false) }

    // Fetch comments when the post is ready
    LaunchedEffect(postId) {
        postId?.let {
            viewModel.fetchComments(it)
        }
    }

    if (postState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
        return
    }

    val post = postState.posts.find { it.id.toString() == postId }
    val user = post?.let { viewModel.getUserById(it.userId) }

    // Display post details and comments
    if (post != null && user != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = post.title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "By: ${user.name}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(bottom = 16.dp))

            Text(text = post.body, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(bottom = 16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Comments", style = MaterialTheme.typography.bodyLarge)
                TextButton(onClick = { showAllComments = !showAllComments }) {
                    Text(text = if (showAllComments) "Show Less" else "Show All")
                }
            }

            // Render comments from state
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
                Text(text = "No comments available.", style = MaterialTheme.typography.bodyLarge)
            }
        }
    } else {
        Text("Post or author not found", style = MaterialTheme.typography.bodyLarge)
    }
}
