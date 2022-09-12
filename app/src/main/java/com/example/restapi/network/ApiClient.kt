package com.example.restapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://jsonplaceholder.typicode.com"

object ApiClient {
    private var retrofit: APIs? = null

    fun getClient(): APIs{
        if( retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIs::class.java)
        }
        return retrofit!!
    }
}