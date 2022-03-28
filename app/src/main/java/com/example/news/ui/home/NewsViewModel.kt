package com.example.news.ui.home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.MainActivity
import com.example.news.api.NewsApi
import com.example.news.api.NewsApiClient
import com.example.news.data.model.Post
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Fragment"
    }
    private var newsApiClient: NewsApi = NewsApiClient.getNewsApi()!!


    val text: LiveData<String> = _text

    private var posts: MutableLiveData<List<Post>> = MutableLiveData<List<Post>>()


//    var posts: MutableList<Post> = mutableListOf(
//        Post(0, "Title", "https://google.com", "https://pbs.twimg.com/profile_images/1388043127592783874/WAzTZ94d_400x400.jpg"),
//        Post(1, "Title1", "https://www.bbc.com/news", "https://pbs.twimg.com/profile_images/1388043127592783874/WAzTZ94d_400x400.jpg"),
//        Post(2, "Title2", "https://github.com/", "https://pbs.twimg.com/profile_images/1388043127592783874/WAzTZ94d_400x400.jpg")
//    )

    fun searchAllPosts(){
        newsApiClient.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                posts.value = response.body()
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                //TODO
            }
        })
    }

    fun searchPostsByCategory(category: String){
        newsApiClient.getPostsByCategory(category).enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                posts.value = response?.body()
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getPostsMutableLiveData(): MutableLiveData<List<Post>>
    {
        return posts
    }


}