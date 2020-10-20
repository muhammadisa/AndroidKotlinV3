package com.xoxoer.postjsonplaceholder.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xoxoer.postjsonplaceholder.model.PostsItem

@Database(entities = [PostsItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}