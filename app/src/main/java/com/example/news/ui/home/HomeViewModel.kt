package com.example.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.model.Post

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val posts: MutableList<Post> = mutableListOf(
        Post(0, "Title", "https://google.com", "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/1f893c7c14424f6f8f72a98101359ecb_9366/Grand_Court_Shoes_White_F36483_01_standard.jpg"),
        Post(1, "Title1", "https://google.com", "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/1f893c7c14424f6f8f72a98101359ecb_9366/Grand_Court_Shoes_White_F36483_01_standard.jpg"),
        Post(2, "Title2", "https://google.com", "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/1f893c7c14424f6f8f72a98101359ecb_9366/Grand_Court_Shoes_White_F36483_01_standard.jpg"),
    )

}