package com.example.news.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.model.Post

class PostAdapter(var allPosts: MutableList<Post>, private val postClickListener: OnPostListener): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val postTitle: TextView
        val postThumbnail: ImageView

        init {
            postTitle = view.findViewById(R.id.postTitleId)
            postThumbnail = view.findViewById(R.id.postThumbnailId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = allPosts[position]

        holder.postTitle.text = currentPost.title
        Glide.with(holder.itemView).load(currentPost.thumbnail).into(holder.postThumbnail)

        holder.itemView.setOnClickListener{
            postClickListener.onPostClick(currentPost)
        }
    }

    override fun getItemCount(): Int {
        return this.allPosts.size
    }

    fun updateData(posts: MutableList<Post>)
    {
        this.allPosts = posts
        notifyDataSetChanged()
    }

}

interface OnPostListener{
    fun onPostClick(post: Post)
}
