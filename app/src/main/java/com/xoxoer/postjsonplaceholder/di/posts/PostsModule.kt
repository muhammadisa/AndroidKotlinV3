package com.xoxoer.postjsonplaceholder.di.posts

import com.xoxoer.postjsonplaceholder.network.PostsApi
import com.xoxoer.postjsonplaceholder.persistence.AppDatabase
import com.xoxoer.postjsonplaceholder.persistence.PostsDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object PostsModule {
    @Provides
    @JvmStatic
    fun providesPostApi(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Provides
    @JvmStatic
    fun providesPostsDao(appDatabase: AppDatabase): PostsDao {
        return appDatabase.postsDao()
    }
}