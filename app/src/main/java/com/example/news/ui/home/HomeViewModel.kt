package com.example.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.model.Post

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Fragment"
    }
    val text: LiveData<String> = _text
    val posts: MutableList<Post> = mutableListOf(
        Post(0, "Title", "https://google.com", "https://pbs.twimg.com/profile_images/1388043127592783874/WAzTZ94d_400x400.jpg"),
        Post(1, "Title1", "https://google.com", "https://pbs.twimg.com/profile_images/1388043127592783874/WAzTZ94d_400x400.jpg"),
        Post(2, "Title2", "https://google.com", "https://pbs.twimg.com/profile_images/1388043127592783874/WAzTZ94d_400x400.jpg")
    )

}