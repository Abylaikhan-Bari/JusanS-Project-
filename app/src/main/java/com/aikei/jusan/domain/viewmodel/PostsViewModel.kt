package com.aikei.jusan.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Comment
import com.aikei.jusan.data.model.Post
import com.aikei.jusan.data.model.User
import com.aikei.jusan.domain.repository.CommentRepository
import com.aikei.jusan.domain.repository.PostRepository
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val users: List<User> = emptyList(),
    val comments: List<Comment> = emptyList(), // Added comments state
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = PostUiState(isLoading = true)
            try {
                val posts = postRepository.getPosts()
                userRepository.getUsers().collect { users ->
                    _uiState.value = PostUiState(posts = posts, users = users, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.value = PostUiState(isLoading = false, error = e.message)
            }
        }
    }

    fun getPostById(postId: String?): Post? {
        return _uiState.value.posts.find { it.id.toString() == postId }
    }

    fun getUserById(userId: Int): User? {
        return _uiState.value.users.find { it.id == userId }
    }

    fun fetchComments(postId: String) {
        viewModelScope.launch {
            try {
                val comments = commentRepository.getCommentsByPostId(postId)
                Log.d("PostsViewModel", "Comments fetched: $comments")
                _uiState.value = _uiState.value.copy(comments = comments)
            } catch (e: Exception) {
                Log.e("PostsViewModel", "Error fetching comments: ${e.message}")
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
