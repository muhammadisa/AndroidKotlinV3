package com.xoxoer.postjsonplaceholder

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.xoxoer.postjsonplaceholder.ui.post.PostActivity
import com.xoxoer.postjsonplaceholder.ui.post.adapter.PostsAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PostRecyclerViewTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(PostActivity::class.java)

    @Test
    fun testPostRecyclerViewOnView() {
        onView(withId(R.id.recyclerViewPosts)).check(matches(isDisplayed()))
    }

    @Test
    fun testPostRecyclerViewCount() {
        onView(withId(R.id.recyclerViewPosts))
            .check(RecyclerViewItemCountAssertion(100))
    }

    @Test
    fun testPostRecyclerViewClickItem() {
        onView(withId(R.id.recyclerViewPosts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<PostsAdapter.PostsViewHolder>(
                    50,
                    click()
                )
            )
    }

}