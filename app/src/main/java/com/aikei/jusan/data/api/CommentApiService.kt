package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.Comment
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApiService {

    // Get comments for a specific post by postId
    @GET("/posts/{postId}/comments")
    suspend fun getCommentsByPostId(@Path("postId") postId: String): List<Comment>

    // Get comments by postId using a query parameter
    @GET("/comments")
    suspend fun getComments(@Query("postId") postId: String): List<Comment>
}
