package com.aikei.jusan.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aikei.jusan.data.model.Post

@Composable
fun PostListItem(post: Post) {
    Text(text = "${post.title} by ${post.author}")
    // Add more UI components to display other details of the post
}
