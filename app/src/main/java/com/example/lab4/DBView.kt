package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.lab4.data.DBconnection
import com.example.lab4.data.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DBView : AppCompatActivity() {
    lateinit var posts: List<String>
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbview)
        job = CoroutineScope(Dispatchers.IO).launch {
            posts = DBconnection.db.postDAO().getAllPosts().map { post -> "${post.userId} - ${post.title} " }
            var listView = findViewById<ListView>(R.id.postsList)
            var adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext,android.R.layout.simple_dropdown_item_1line,posts)
            listView.adapter = adapter
        }



    }
}