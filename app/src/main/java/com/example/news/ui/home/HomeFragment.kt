package com.example.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.ui.adapter.PostAdapter

class HomeFragment : Fragment() {

    private lateinit var HomeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: PostAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        HomeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postRecyclerView = view.findViewById(R.id.postsRecyclerView)
        postRecyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter = PostAdapter(HomeViewModel.posts)
        postRecyclerView.setHasFixedSize(true)
        postRecyclerView.adapter = recyclerViewAdapter
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
