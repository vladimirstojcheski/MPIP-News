package com.example.news.data.model

import com.google.gson.annotations.SerializedName

data class Post(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("postUrl")
    val postUrl: String,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("haveExtra")
    val haveExtra: Boolean,

    @SerializedName("isDeleted")
    val isDeleted: Boolean
)