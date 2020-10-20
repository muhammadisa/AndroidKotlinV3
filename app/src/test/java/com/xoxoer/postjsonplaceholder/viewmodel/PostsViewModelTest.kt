package com.xoxoer.postjsonplaceholder.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.xoxoer.postjsonplaceholder.MockUtil
import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.network.PostsApi
import com.xoxoer.postjsonplaceholder.persistence.PostsDao
import com.xoxoer.postjsonplaceholder.ui.post.PostRepository
import com.xoxoer.postjsonplaceholder.ui.post.PostsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {

    private lateinit var viewModel: PostsViewModel
    private lateinit var postRepository: PostRepository
    private lateinit var listPosts: List<PostsItem>

    private val postsApi: PostsApi = mock()
    private val postsDao: PostsDao = mock()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        postRepository = PostRepository(postsApi, postsDao)
        viewModel = mock()
        listPosts = listOf(
            MockUtil.mockPost(1, 2),
            MockUtil.mockPost(3, 4),
            MockUtil.mockPost(5, 6),
            MockUtil.mockPost(7, 8)
        )
    }

    @Test
    fun retrievePosts() = runBlocking {
        val observer: Observer<List<PostsItem>> = mock()
        val fetchedPosts = MutableLiveData<List<PostsItem>>()

        fetchedPosts.postValue(listPosts)
        fetchedPosts.observeForever(observer)

        verify(observer).onChanged(listPosts)
        fetchedPosts.removeObserver(observer)
    }

}