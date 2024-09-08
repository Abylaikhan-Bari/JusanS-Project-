package com.aikei.jusan.domain.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Post
import com.aikei.jusan.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: PostRepository = PostRepository()) : ViewModel() {

    private val _posts = mutableStateOf<List<Post>>(emptyList())
    val posts: State<List<Post>> = _posts // Expose as a State

    var isLoading by mutableStateOf(true)
    var error by mutableStateOf<String?>(null)

    init {
        viewModelScope.launch {
            isLoading = true
            try {
                _posts.value = repository.getPosts() // Update _posts
            } catch (e: Exception) {
                error = "Error fetching posts: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
