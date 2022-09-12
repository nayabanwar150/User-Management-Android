package com.example.restapi.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.restapi.models.user.Users
import com.example.restapi.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.restapi.network.Result

class UserRepo {
    private val client = ApiClient.getClient()

    private val _users = MutableLiveData<Result<Users>>()
    val users: LiveData<Result<Users>> = _users

    fun getUsers(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _users.postValue(Result.Loading())
                val response = client.getUsers()
                if (response != null){
                    _users.postValue(Result.Success(response.body()))
                }else{
                    _users.postValue(Result.Failure(errMsg = "No users found!"))
                }
            }catch (e: Exception){
                Log.e("exp", e.message.toString())
                _users.postValue(Result.Failure(e.message))
            }
        }
    }
}