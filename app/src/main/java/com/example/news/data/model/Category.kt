package com.example.news.data.model

data class Category(
    val id: Long,
    val name: String,
    val url: String,
    val posts: List<Post>
)