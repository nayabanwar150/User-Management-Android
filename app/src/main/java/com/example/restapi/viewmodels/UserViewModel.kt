package com.example.restapi.viewmodels

import androidx.lifecycle.ViewModel
import com.example.restapi.repos.UserRepo

class UserViewModel: ViewModel() {
    private val repo = UserRepo()
    init {
        repo.getUsers()
    }

    val users = repo.users
}