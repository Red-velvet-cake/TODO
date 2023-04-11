package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.red_velvet_cake.dailytodo.domain.TodoService
import okhttp3.*
import java.io.IOException

class TodoServiceImpl: TodoService {
    // token just test
     val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjRmODg2NTIzLTUwNWItNDEzMy05NjEzLWYxZTA1ODVhMzFiOCIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMjM4Njc4fQ.D5DB1QmEo7ELXB8exmOSj9xYjEQi7fDCTti-__3BUuk"
    val client = OkHttpClient()
    override fun getAllPersonalTodos(){
        Log.i("iii" , "make request")
        val request = Request.Builder().url("https://team-todo-62dmq.ondigitalocean.app/todo/personal").header("Authorization","Bearer $TOKEN" ) .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i("iii" , "fail ${e.message}")

            }
            override fun onResponse(call: Call, response: Response) {
                Log.i("iii" , response.body?.string().toString())
            }
        })
    }

}