package com.xoxoer.postjsonplaceholder.di.posts

import androidx.lifecycle.ViewModel
import com.xoxoer.postjsonplaceholder.di.ViewModelKey
import com.xoxoer.postjsonplaceholder.ui.post.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PostsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel): ViewModel
}