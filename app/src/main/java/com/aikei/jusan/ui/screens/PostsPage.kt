package com.aikei.jusan.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aikei.jusan.ui.components.PostListItem
import com.aikei.jusan.ui.components.TopAppBar
import com.aikei.jusan.viewmodel.PostsViewModel
import com.aikei.jusan.data.model.Post

@Composable
fun PostsPage(viewModel: PostsViewModel) {
    val posts = viewModel.posts

    Scaffold(

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(posts) { post ->
                PostListItem(post)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostsPagePreview() {
    val dummyPosts = listOf(
        Post(id = "1", title = "Post 1", content = "Content of post 1", author = "Author 1", timestamp = 1627880400000L),
        Post(id = "2", title = "Post 2", content = "Content of post 2", author = "Author 2", timestamp = 1627884000000L)
    )

    // Create a dummy view model with the dummy posts
    val dummyViewModel = object : PostsViewModel() {
        override val posts = dummyPosts
    }

    PostsPage(viewModel = dummyViewModel)
}
