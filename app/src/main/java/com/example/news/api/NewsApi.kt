package com.example.news.api

import com.example.news.data.model.Category
import com.example.news.data.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {
    @GET("/category/all")
    fun getAllCategories(): Call<List<Category>>

    @GET("/category/{categoryName}")
    fun getCategoryByName(@Path("categoryName") categoryName: String): Call<Category>

    @GET("/category/{categoryId}")
    fun getCategoryById(@Path("categoryId") categoryId: String): Call<Category>

    @GET("/posts/{category}")
    fun getPostsByCategory(@Path("category") category: String): Call<List<Post>>

    @GET("/posts/pagination")
    fun getNewestPosts(@Path("categoryId") categoryId: String): Call<List<Post>>

    @GET("/posts/pagination/all")
    fun getAllPosts(): Call<List<Post>>
}