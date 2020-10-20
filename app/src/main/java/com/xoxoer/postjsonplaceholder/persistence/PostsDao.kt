package com.xoxoer.postjsonplaceholder.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xoxoer.postjsonplaceholder.model.PostsItem
import io.reactivex.Single

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(postsItem: PostsItem)

    @Query("SELECT * FROM posts WHERE title LIKE :query_")
    fun getPostWithQuery(query_: String): Single<List<PostsItem>>

    @Query("SELECT * FROM posts")
    fun getPosts(): Single<List<PostsItem>>

    @Query("SELECT * FROM posts WHERE id = :id_")
    fun getPost(id_: Int): Single<PostsItem>

    @Query("DELETE FROM posts")
    fun deletePosts()

}