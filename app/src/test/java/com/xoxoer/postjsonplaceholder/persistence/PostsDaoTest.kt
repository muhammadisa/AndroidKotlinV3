package com.xoxoer.postjsonplaceholder.persistence

import com.xoxoer.postjsonplaceholder.MockUtil
import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.util.common.toWhereLikeFormat
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class PostsDaoTest : LocalDatabase() {

    private lateinit var postsDao: PostsDao
    private lateinit var listPosts: List<PostsItem>

    @Before
    fun init() {
        postsDao = db.postsDao()
        listPosts = listOf(
            MockUtil.mockPost(1, 2),
            MockUtil.mockPost(3, 4),
            MockUtil.mockPost(5, 6),
            MockUtil.mockPost(7, 8),
            MockUtil.mockPostForWhereLike(9, 10, "This is body1", "kotlin 1"),
            MockUtil.mockPostForWhereLike(11, 12, "This is body2", "java 2")
        )
        listPosts.forEach { post ->
            postsDao.insertPost(post)
        }
    }

    @Test
    fun loadAllPostsFromRoomDatabase() {
        val loadedPosts = postsDao.getPosts().blockingGet()
        loadedPosts.forEach { loadedPost ->
            assertThat(loadedPost.id, `is`(loadedPost.id))
            assertThat(loadedPost.userId, `is`(loadedPost.userId))
            assertThat(loadedPost.title, `is`(loadedPost.title))
            assertThat(loadedPost.body, `is`(loadedPost.body))
        }
    }

    @Test
    fun loadPostFromRoomDatabase() {
        val loadedPost = postsDao.getPost(7).blockingGet()
        assertThat(loadedPost.id, `is`(7))
        assertThat(loadedPost.userId, `is`(8))
        assertThat(loadedPost.title, `is`(MockUtil.TITLE))
        assertThat(loadedPost.body, `is`(MockUtil.BODY))
    }

    @Test
    fun loadPostWithWhereLikeFromRoomDatabase() {
        val loadedPostQueryOne = postsDao
            .getPostWithQuery("kotlin".toWhereLikeFormat())
            .blockingGet()

        val loadedPostQueryTwo = postsDao
            .getPostWithQuery("java".toWhereLikeFormat())
            .blockingGet()

        assertThat(loadedPostQueryOne.size, `is`(1))
        loadedPostQueryOne.forEach { post ->
            assertThat(post.id, `is`(9))
            assertThat(post.userId, `is`(10))
            assertThat(post.title, `is`("kotlin 1"))
            assertThat(post.body, `is`("This is body1"))
        }

        assertThat(loadedPostQueryTwo.size, `is`(1))
        loadedPostQueryTwo.forEach { post ->
            assertThat(post.id, `is`(11))
            assertThat(post.userId, `is`(12))
            assertThat(post.title, `is`("java 2"))
            assertThat(post.body, `is`("This is body2"))
        }
    }

    @Test
    fun deleteAllPostsFromDatabase() {
        postsDao.deletePosts()
        val loadedPosts = postsDao.getPosts().blockingGet()
        assertThat(loadedPosts.size, `is`(0))
    }

}