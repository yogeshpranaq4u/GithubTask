package com.example.newsapp

import com.example.newsapp.interfaces.PostInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostDataHelper {
    private val base_url = "https://api.github.com/orgs/ORG/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val apiClient = retrofit.create(PostInterface::class.java)

}