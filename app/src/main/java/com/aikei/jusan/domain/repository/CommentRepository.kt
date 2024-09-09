package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.api.CommentApiService
import com.aikei.jusan.data.model.Comment
import javax.inject.Inject

interface CommentRepository {
    suspend fun getCommentsByPostId(postId: String): List<Comment>
}

class CommentRepositoryImpl @Inject constructor(
    private val apiService: CommentApiService
) : CommentRepository {
    override suspend fun getCommentsByPostId(postId: String): List<Comment> {
        return apiService.getCommentsByPostId(postId)
    }
}
