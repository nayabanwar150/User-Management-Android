package com.example.restapi.models.posts

data class PostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)