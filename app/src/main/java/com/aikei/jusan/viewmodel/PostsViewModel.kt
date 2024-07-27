package com.aikei.jusan.viewmodel

import androidx.lifecycle.ViewModel
import com.aikei.jusan.data.model.Post
import com.aikei.jusan.data.repository.PostRepository

class PostsViewModel : ViewModel() {
    private val postRepository = PostRepository()
    val posts: List<Post> = postRepository.getPosts()
}
