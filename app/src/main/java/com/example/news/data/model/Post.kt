package com.example.news.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class Post(
    val id: Long,
    val title: String,
    val postUrl: String,
    val thumbnail: String
)