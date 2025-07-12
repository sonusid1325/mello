package com.sonusid.mello.data.posts

import com.sonusid.mello.domain.models.Post

interface PostRepository {
    suspend fun getAllPosts(): List<Post>
}
