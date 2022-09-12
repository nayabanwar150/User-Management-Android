package com.example.restapi.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.restapi.models.comments.Comments
import com.example.restapi.models.posts.Posts
import com.example.restapi.network.ApiClient
import com.example.restapi.network.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PostsRepo {
    private val client = ApiClient.getClient()

    private val _userPosts = MutableLiveData<Result<Posts>>()
    val userPosts: LiveData<Result<Posts>> = _userPosts

//    private val _postComment = MutableLiveData<Result<Comments>>()
//    val postComment: LiveData<Result<Comments>> = _postComment

    fun getUserPosts(userId: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _userPosts.postValue(Result.Loading())
                val response = client.getUserPosts(userId)
                if(response != null){
                    _userPosts.postValue(Result.Success(response.body()))
                }else{
                    _userPosts.postValue(Result.Failure("Unable to fetch posts!"))
                }
            }catch (e: Exception){
                _userPosts.postValue(Result.Failure(e.message))
            }
        }
    }

    fun getPostComment(postId: String, comments: MutableLiveData<Result<Comments>>){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = client.getPostComment(postId)
                if(response != null){
                    comments.postValue(Result.Success(response.body()))
                }else{
                    comments.postValue(Result.Failure("Unable to fetch comments!"))
                }
            }catch (e: Exception){
                comments.postValue(Result.Failure(e.message))
            }
        }
    }
}