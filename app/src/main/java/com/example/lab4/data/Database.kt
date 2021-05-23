package com.example.lab4.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun postDAO(): PostDAO
}