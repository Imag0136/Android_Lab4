package com.example.lab4.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDAO {
    @Query("SELECT * FROM post")
    fun getAllPosts(): List<Post>

    @Insert
    fun insertAll(posts: List<Post>)

    @Query("DELETE FROM post")
    fun deleteAll()
}