package com.example.news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {

    companion object {
        private var newsApi: NewsApi? = null

        fun getNewsApi(): NewsApi? {

            if (newsApi == null) {
                newsApi = Retrofit.Builder()
                    .baseUrl("http://localhost:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewsApi::class.java)
            }
            return newsApi
        }

    }
}