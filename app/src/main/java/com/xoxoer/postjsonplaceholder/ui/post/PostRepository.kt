package com.xoxoer.postjsonplaceholder.ui.post

import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.network.PostsApi
import com.xoxoer.postjsonplaceholder.persistence.PostsDao
import com.xoxoer.postjsonplaceholder.util.apisingleobserver.ApiSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postsApi: PostsApi,
    private val postsDao: PostsDao
) {
    private fun persistPosts(posts: List<PostsItem>) {
        posts.forEach { post -> postsDao.insertPost(post) }
    }

    fun retrievePostsWithQuery(
        query: String,
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<List<PostsItem>>
    ) {
        postsDao.getPostWithQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(handler)
    }

    fun retrievePosts(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        handler: ApiSingleObserver<List<PostsItem>>
    ) {
        return postsApi.retrievePosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .doOnSuccess { posts -> persistPosts(posts) }
            .doOnError {
                postsDao.getPosts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onStart() }
                    .doOnTerminate { onFinish() }
                    .subscribe(handler)
            }
            .subscribe(handler)
    }
}