package com.sonusid.mello.domain.use_cases

import com.sonusid.mello.data.posts.PostRepository
import com.sonusid.mello.domain.models.Post
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<Post> {
        return repository.getAllPosts()
    }
}
