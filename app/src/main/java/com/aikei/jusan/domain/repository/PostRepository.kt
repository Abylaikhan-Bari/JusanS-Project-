package com.aikei.jusan.domain.repository

import com.aikei.jusan.data.model.Post

class PostRepository {
    fun getPosts(): List<Post> {
        // Simulating data fetching, will replace with actual data source logic
        return listOf(
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("1", "Post Title 1", "Post Content 1", "Author 1", 1624032000),
            Post("2", "Post Title 2", "Post Content 2", "Author 2", 1624035600)
        )
    }
}
