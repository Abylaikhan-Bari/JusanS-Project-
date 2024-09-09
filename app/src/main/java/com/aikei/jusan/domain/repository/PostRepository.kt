package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.api.PostApiService
import com.aikei.jusan.data.model.Post
import javax.inject.Inject

interface PostRepository {
    suspend fun getPosts(): List<Post>
}

class PostRepositoryImpl @Inject constructor(
    private val apiService: PostApiService
) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}

