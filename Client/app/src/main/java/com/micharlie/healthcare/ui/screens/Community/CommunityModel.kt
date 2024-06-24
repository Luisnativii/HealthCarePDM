package com.micharlie.healthcare.ui.screens.Community

data class Post(
    val id: String,
    val authorId: String,
    val content: String,
    val timestamp: Long,
    val isApproved: Boolean
)

data class Comment(
    val id: String,
    val postId: String,
    val authorId: String,
    val content: String,
    val timestamp: Long
)
