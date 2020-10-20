package com.xoxoer.postjsonplaceholder.ui.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xoxoer.postjsonplaceholder.R
import com.xoxoer.postjsonplaceholder.databinding.CardViewPostBinding
import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.util.AdapterContract

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(), AdapterContract {

    private val posts: MutableList<PostsItem> = mutableListOf()

    @Suppress("UNCHECKED_CAST")
    override fun <T> add(data: T?) {
        this.posts.clear()
        this.posts.addAll(data as List<PostsItem>)
        notifyDataSetChanged()
    }

    inner class PostsViewHolder(val binding: CardViewPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<CardViewPostBinding>(
                inflater,
                R.layout.card_view_post,
                parent,
                false
            )
        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.apply {
            postItem = post
            executePendingBindings()
        }
    }

}