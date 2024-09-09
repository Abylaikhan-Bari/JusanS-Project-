package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.Comment
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentApiService {
    @GET("posts/{postId}/comments")
    suspend fun getCommentsByPostId(@Path("postId") postId: String): List<Comment>
}
