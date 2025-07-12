package com.sonusid.mello.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonusid.mello.domain.models.Post
import com.sonusid.mello.data.posts.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _feed = MutableStateFlow<List<Post>>(emptyList())
    val feed: StateFlow<List<Post>> = _feed

    init {
        loadFeed()
    }

    private fun loadFeed() {
        viewModelScope.launch {
            _feed.value = postRepository.getAllPosts()
        }
    }
}