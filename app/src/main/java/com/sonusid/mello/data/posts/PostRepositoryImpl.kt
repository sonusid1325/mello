package com.sonusid.mello.data.posts

import com.sonusid.mello.domain.models.Post
import kotlinx.coroutines.delay

class PostRepositoryImpl : PostRepository {
    override suspend fun getAllPosts(): List<Post> {
        delay(1000) // simulate network
        return listOf(
            Post(id = "1", username = "ソヌ", content = "Building Mello with Jetpack Compose 💖"),
            Post(id = "2", username = "hydra_dev", content = "Working on my own shell using Bun 🐢")
        )
    }
}
