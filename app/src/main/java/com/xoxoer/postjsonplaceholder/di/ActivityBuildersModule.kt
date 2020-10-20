package com.xoxoer.postjsonplaceholder.di

import com.xoxoer.postjsonplaceholder.di.posts.PostsModule
import com.xoxoer.postjsonplaceholder.di.posts.PostsViewModelModule
import com.xoxoer.postjsonplaceholder.ui.post.PostActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            PostsViewModelModule::class,
            PostsModule::class
        ]
    )
    abstract fun contributePostActivity(): PostActivity

}