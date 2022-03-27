package com.example.news.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.model.Category
import com.example.news.data.model.Post

class CategoryAdapter(var allCategories: MutableList<Category>, private val categoryClickListener: OnCategoryListener): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val category: TextView

        init {
            category = view.findViewById(R.id.category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCategory = allCategories[position]

        holder.category.text = currentCategory.name.uppercase()

        holder.itemView.setOnClickListener{
            categoryClickListener.onCategoryClick(currentCategory)
        }
    }

    override fun getItemCount(): Int {
        return this.allCategories.size
    }

    fun updateData(categories: MutableList<Category>)
    {
        this.allCategories = categories
        notifyDataSetChanged()
    }

}

interface OnCategoryListener{
    fun onCategoryClick(category: Category)
}