package com.example.restapi.network

sealed class Result<T>(val data:T? = null, val errMsg: String? = null) {
    class Loading<T> : Result<T>()
    class Success<T>(data: T? = null) : Result<T>(data = data)
    class Failure<T>(errMsg: String? = null) : Result<T>(errMsg = errMsg)
}