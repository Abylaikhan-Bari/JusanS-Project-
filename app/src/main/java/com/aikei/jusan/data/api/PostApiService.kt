package com.aikei.jusan.data.api

import com.aikei.jusan.data.model.Post
import retrofit2.http.GET

interface PostApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}
