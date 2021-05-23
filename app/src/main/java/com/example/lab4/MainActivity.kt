package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lab4.data.DBconnection
import com.example.lab4.data.Database
import com.example.lab4.data.Post
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    lateinit var post: List<Post>
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DBconnection.db = Room.databaseBuilder(
            applicationContext, Database::class.java, "posts-db"
        ).build()


    }

    fun request(view: View) {
        val textView = findViewById<TextView>(R.id.userId)
        if (textView.text.isEmpty())
            Toast.makeText(this, "Пустая сторока!!", Toast.LENGTH_LONG).show()
        else {
            val queue = Volley.newRequestQueue(this)
            val url = "https://jsonplaceholder.typicode.com/users/${textView.text}/posts"

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    //  println(response)
                    post = Json.decodeFromString<List<Post>>(response)
                    job = CoroutineScope(Dispatchers.IO).launch {
                        DBconnection.db.clearAllTables()
                        DBconnection.db.postDAO().insertAll(post)
                    }
                    //println(post)
                },
                { println("That didn't work!") })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }


    }

//    suspend fun addToBase(post: List<Post>) {
//        coroutineScope {
//            DBconnection.db.postDAO().insertAll(post)
//        }
//    }

    fun viewDB(view: View) {
        var a = Intent(this, DBView::class.java)
        startActivity(a)
    }

    fun clearDB(view: View) {
        job = CoroutineScope(Dispatchers.IO).launch {
            DBconnection.db.clearAllTables()
        }
    }

}