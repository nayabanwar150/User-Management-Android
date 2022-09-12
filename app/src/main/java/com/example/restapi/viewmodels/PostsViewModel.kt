package com.example.restapi.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restapi.models.comments.Comments
import com.example.restapi.network.Result
import com.example.restapi.repos.PostsRepo

class PostsViewModel: ViewModel() {
    private val repo = PostsRepo()
    val userPost = repo.userPosts

    fun getUserPosts(userId: String) = repo.getUserPosts(userId)

    fun getPostComment(postId: String, comments: MutableLiveData<Result<Comments>>) = repo.getPostComment(postId, comments)
}