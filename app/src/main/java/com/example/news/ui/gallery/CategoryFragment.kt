package com.example.news.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.data.model.Category
import com.example.news.databinding.FragmentCategoryBinding
import com.example.news.ui.adapter.CategoryAdapter
import com.example.news.ui.adapter.OnCategoryListener
import com.example.news.ui.home.NewsFragment

class CategoryFragment : Fragment(), OnCategoryListener {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var categoryViewModel: CategoryViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoryViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel.searchAllCategories()

        categoryViewModel.getCategoriesMutableLiveData()
            .observe(viewLifecycleOwner,
                { t ->
                    if (t != null){
                        displayData(t as MutableList<Category>)
                    } else{
                        Toast.makeText(activity, "ERROR!!", Toast.LENGTH_LONG).show()
                    }
                })

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)
        categoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter = CategoryAdapter(mutableListOf(), this)
        categoryRecyclerView.setHasFixedSize(true)
        categoryRecyclerView.adapter = recyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryClick(category: Category) {
        val activity = activity as MainActivity
        activity.setCategory(category.name)
        val result = category.name
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        findNavController().navigate(R.id.action_nav_gallery_to_nav_home)
    }

    private fun displayData(categories: MutableList<Category>){
        recyclerViewAdapter.updateData(categories)
    }
}