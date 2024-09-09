package com.aikei.jusan.presentation.ui.screens.posts

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aikei.jusan.data.model.Comment
import com.aikei.jusan.domain.viewmodel.PostsViewModel

@Composable
fun PostDetailsPage(postId: String?, viewModel: PostsViewModel = hiltViewModel()) {
    val postState by viewModel.uiState.collectAsState()
    val post = postState.posts.find { it.id.toString() == postId }
    val user = post?.let { postState.users.find { it.id == post.userId } }

    var comments by remember { mutableStateOf(emptyList<Comment>()) }
    var showAllComments by remember { mutableStateOf(false) }

    // Use LaunchedEffect to call suspend functions
    LaunchedEffect(postId) {
        post?.let {
            comments = viewModel.getCommentsByPostId(it.id.toString())
        }
    }

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
            Row {
                Text(
                    text = "By: ",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.Red
                )
            }
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Comments", style = MaterialTheme.typography.bodyLarge)
                TextButton(onClick = { showAllComments = !showAllComments }) {
                    Text(text = if (showAllComments) "Show Less" else "Show All")
                }
            }

            AnimatedVisibility(
                visible = showAllComments,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column {
                    comments.forEach { comment ->
                        CommentCard(comment = comment)
                    }
                }
            }

            if (!showAllComments && comments.isNotEmpty()) {
                comments.take(3).forEach { comment ->
                    CommentCard(comment = comment)
                }
                if (comments.size > 3) {
                    TextButton(onClick = { showAllComments = true }) {
                        Text("Show All")
                    }
                }
            }
        }
    } else {
        Text("Post or author not found")
    }
}
