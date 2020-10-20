package com.xoxoer.postjsonplaceholder.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.xoxoer.postjsonplaceholder.MockUtil
import com.xoxoer.postjsonplaceholder.RxTrampolineSchedulerRule
import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.network.PostsApi
import com.xoxoer.postjsonplaceholder.persistence.PostsDao
import com.xoxoer.postjsonplaceholder.ui.post.PostRepository
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PostRepositoryTest {

    private lateinit var repository: PostRepository
    private lateinit var listPosts: List<PostsItem>

    private val postsApi: PostsApi = mock()
    private val postsDao: PostsDao = mock()

    @Rule
    @JvmField
    var testSchedulerRule = RxTrampolineSchedulerRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = PostRepository(postsApi, postsDao)
        listPosts = listOf(
            MockUtil.mockPost(1, 2),
            MockUtil.mockPost(3, 4),
            MockUtil.mockPost(5, 6),
            MockUtil.mockPost(7, 8)
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun retrievePostsNetworkAndDatabase() = runBlocking {
        whenever(postsDao.getPosts()).thenReturn(Single.just(listPosts))
        postsDao.getPosts().blockingGet().forEach { post ->
            assertThat(post.id, `is`(post.id))
            assertThat(post.userId, `is`(post.userId))
            assertThat(post.title, `is`(post.title))
            assertThat(post.body, `is`(post.body))
        }

        whenever(postsApi.retrievePosts()).thenReturn(Single.just(listPosts))
        postsApi.retrievePosts().blockingGet().forEach { post ->
            assertThat(post.id, `is`(post.id))
            assertThat(post.userId, `is`(post.userId))
            assertThat(post.title, `is`(post.title))
            assertThat(post.body, `is`(post.body))
        }
    }
}