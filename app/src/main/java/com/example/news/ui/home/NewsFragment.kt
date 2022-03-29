package com.example.news.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.api.NewsApi
import com.example.news.api.NewsApiClient
import com.example.news.data.model.Category
import com.example.news.data.model.Post
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.ui.adapter.OnPostListener
import com.example.news.ui.adapter.PostAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope

class NewsFragment : Fragment(), OnPostListener {

    private lateinit var newsViewModel: NewsViewModel
    private var _binding: FragmentNewsBinding? = null

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: PostAdapter

    private lateinit var newsApiClient: NewsApi

    private val mAuth = FirebaseAuth.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as MainActivity
        val selectedCategory = activity.getCategory()

//        val userTextView: TextView = view.findViewById<TextView>(R.id.userTextView)
//        if (mAuth.currentUser != null)
//            userTextView.text = mAuth.currentUser!!.email

        if (selectedCategory.isNullOrEmpty())
            newsViewModel.searchAllPosts()
        else{
            newsViewModel.searchPostsByCategory(selectedCategory)
        }

        newsViewModel.getPostsMutableLiveData()
            .observe(viewLifecycleOwner,
                { t ->
                    if (t != null){
                        displayData(t as MutableList<Post>)
                    } else{
                        Toast.makeText(activity, "ERROR!!", Toast.LENGTH_LONG).show()
                    }
                })

        postRecyclerView = view.findViewById(R.id.postsRecyclerView)
        postRecyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter = PostAdapter(mutableListOf<Post>(), this)
        postRecyclerView.setHasFixedSize(true)
        postRecyclerView.adapter = recyclerViewAdapter
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPostClick(post: Post) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(post.postUrl)
        startActivity(intent)
    }


//    private fun searchAllPosts(){
//        newsApiClient.getAllPosts().enqueue(object : Callback<List<Post>> {
//            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
//                displayData(response.body())
//            }
//
//            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
//                Toast.makeText(activity, t?.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
    private fun displayData(posts: MutableList<Post>)
    {
        recyclerViewAdapter.updateData(posts)
    }
}
