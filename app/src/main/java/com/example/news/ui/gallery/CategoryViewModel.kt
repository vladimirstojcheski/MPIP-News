package com.example.news.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.api.NewsApi
import com.example.news.api.NewsApiClient
import com.example.news.data.model.Category
import com.example.news.data.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CategoryViewModel : ViewModel() {

    private var newsApiClient: NewsApi = NewsApiClient.getNewsApi()!!

    private var categories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()


    fun searchAllCategories() {
        newsApiClient.getAllCategories().enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                categories.value = response.body()
            }

            override fun onFailure(call: Call<List<Category>>?, t: Throwable?) {
                //TODO
            }
        })
    }

    fun getCategoriesMutableLiveData(): MutableLiveData<List<Category>>{
        return categories
    }
}