package com.xoxoer.postjsonplaceholder.network

import com.xoxoer.postjsonplaceholder.model.PostsItem
import io.reactivex.Single
import retrofit2.http.GET

interface PostsApi {
    @GET("/posts")
    fun retrievePosts(): Single<List<PostsItem>>
}