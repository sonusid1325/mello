package com.sonusid.mello.domain.models

data class Post(
    val id: String = "",
    val username: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val imageUrl: String? = null,
    val codeSnippet: String? = null
)

