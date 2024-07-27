package com.aikei.jusan.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aikei.jusan.ui.components.PostListItem
import com.aikei.jusan.ui.components.TopAppBar
import com.aikei.jusan.viewmodel.PostsViewModel

@Composable
fun PostsPage(viewModel: PostsViewModel) {
//    val posts = viewModel.posts
//
//    Scaffold(
//        topBar = { TopAppBar(title = "Posts") }
//    ) { paddingValues ->
//        LazyColumn(modifier = Modifier.padding(paddingValues)) {
//            items(posts) { post ->
//                PostListItem(post)
//            }
//        }
//    }
}
