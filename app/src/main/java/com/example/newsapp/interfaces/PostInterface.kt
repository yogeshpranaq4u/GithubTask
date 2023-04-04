package com.example.newsapp.interfaces

import com.example.newsapp.postdata.PostData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PostInterface {
    @FormUrlEncoded
    @POST("repos")
    fun postData(@Field("name") reponame: String): Call<PostData>
}