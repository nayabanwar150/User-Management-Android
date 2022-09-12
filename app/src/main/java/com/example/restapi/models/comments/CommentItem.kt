package com.example.restapi.models.comments

data class CommentItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)