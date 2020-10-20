package com.xoxoer.postjsonplaceholder.network

import io.reactivex.schedulers.Schedulers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsApiTest : ApiAbstract<PostsApi>() {

    private lateinit var service: PostsApi

    @Before
    fun setup() {
        service = createService(PostsApi::class.java)
    }

    @Test
    fun retrievePostsAndCheckSize() {
        // given
        enqueueResponse("/Posts.json")
        val response = service.retrievePosts()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        mockWebServer.takeRequest()

        // test
        assertThat(response.size, `is`(4))
    }

    @Test
    fun retrievePostsAndCheckItems() {
        // given
        enqueueResponse("/Posts.json")
        val response = service.retrievePosts()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .blockingGet()
        mockWebServer.takeRequest()

        // test
        assertThat(response.size, `is`(4))
        response.forEach { post ->
            assertThat(post.id, `is`(post.id))
            assertThat(post.userId, `is`(post.userId))
            assertThat(post.title, `is`(post.title))
            assertThat(post.body, `is`(post.body))
        }
    }

}