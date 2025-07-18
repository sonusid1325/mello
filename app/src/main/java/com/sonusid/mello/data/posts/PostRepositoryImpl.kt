package com.sonusid.mello.data.posts

import com.sonusid.mello.domain.models.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor() : PostRepository {
    override suspend fun getAllPosts(): List<Post> {
        // Replace this with real data fetching (Firebase, API, etc.)
        return listOf(
            Post(id = "1", username = "ソヌ", content = "Hello Mello!"),
            Post(id = "2", username = "Bunny", content = "This is lit 🔥")
        )
    }
}
