package com.xoxoer.postjsonplaceholder

import com.xoxoer.postjsonplaceholder.model.PostsItem

object MockUtil {
    const val TITLE = "Title of this post item"
    const val BODY = "This is body of post item"

    fun mockPost(id: Int, userId: Int) = PostsItem(
        id,
        BODY,
        TITLE,
        userId
    )

    fun mockPostForWhereLike(
        id: Int,
        userId: Int,
        body: String,
        title: String
    ) = PostsItem(
        id,
        body,
        title,
        userId
    )
}