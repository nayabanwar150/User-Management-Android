package com.example.restapi.network

import com.example.restapi.models.comments.Comments
import com.example.restapi.models.posts.Posts
import com.example.restapi.models.user.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("users")
    suspend fun getUsers(): Response<Users>

    @GET("posts")
    suspend fun getUserPosts(@Query("userId") userId: String): Response<Posts>

    @GET("comments")
    suspend fun getPostComment(@Query("postId") postId: String): Response<Comments>
}