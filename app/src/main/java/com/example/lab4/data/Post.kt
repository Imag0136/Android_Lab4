package com.example.lab4.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "post")
data class Post (
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String


    )