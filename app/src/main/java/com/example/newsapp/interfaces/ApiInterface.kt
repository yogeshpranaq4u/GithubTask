package com.example.newsapp.interfaces

import com.example.newsapp.model.Data
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @GET("repositories")
    fun getExercise(): Call<Data?>

}