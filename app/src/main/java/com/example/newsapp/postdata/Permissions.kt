package com.example.newsapp.postdata

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)