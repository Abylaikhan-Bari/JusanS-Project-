package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Post
import com.aikei.jusan.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState(isLoading = true))
    val uiState: StateFlow<PostUiState> = _uiState

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val posts = repository.getPosts()
                _uiState.value = PostUiState(posts = posts, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = PostUiState(
                    isLoading = false,
                    error = "Error fetching posts: ${e.message}"
                )
            }
        }
    }

    fun getPostById(postId: String?): Post? {
        val postIdInt = postId?.toIntOrNull()
        return _uiState.value.posts.find { it.id == postIdInt }
    }

}
