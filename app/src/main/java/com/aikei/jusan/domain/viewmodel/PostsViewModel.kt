package com.aikei.jusan.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aikei.jusan.data.model.Post
import com.aikei.jusan.data.model.User
import com.aikei.jusan.domain.repository.PostRepository
import com.aikei.jusan.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // Collect posts and users from repositories
            val posts = postRepository.getPosts()
            // Collect users from the Flow
            userRepository.getUsers().collect { users ->
                _uiState.value = PostUiState(posts = posts, users = users)
            }
        }
    }

    fun getPostById(postId: String?): Post? {
        val posts = _uiState.value.posts
        return posts.find { it.id.toString() == postId }
    }

    fun getUserById(userId: Int): User? {
        val users = _uiState.value.users
        return users.find { it.id == userId }
    }

    data class PostUiState(
        val posts: List<Post> = emptyList(),
        val users: List<User> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
