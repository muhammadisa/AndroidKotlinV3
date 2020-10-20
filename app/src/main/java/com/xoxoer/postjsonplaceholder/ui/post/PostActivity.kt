package com.xoxoer.postjsonplaceholder.ui.post

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.xoxoer.postjsonplaceholder.R
import com.xoxoer.postjsonplaceholder.base.BaseActivity
import com.xoxoer.postjsonplaceholder.databinding.ActivityPostBinding
import com.xoxoer.postjsonplaceholder.ui.post.adapter.PostsAdapter
import com.xoxoer.postjsonplaceholder.util.common.createLoading
import com.xoxoer.postjsonplaceholder.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class PostActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var loadingDialog: Dialog
    lateinit var postsViewModel: PostsViewModel

    private val binding: ActivityPostBinding by binding(R.layout.activity_post)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = createLoading()
        postsViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(PostsViewModel::class.java)
        binding.apply {
            lifecycleOwner = this@PostActivity
            postAdapter = PostsAdapter()
            vm = postsViewModel
        }

        // call get network call retrofit
        postsViewModel.retrievePosts()
    }
}