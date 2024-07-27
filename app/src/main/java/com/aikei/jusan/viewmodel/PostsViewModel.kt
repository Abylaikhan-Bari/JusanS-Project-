package com.aikei.jusan.viewmodel

import androidx.lifecycle.ViewModel
import com.aikei.jusan.data.model.Post

open class PostsViewModel : ViewModel() {
    open val posts: List<Post> = emptyList()
}
