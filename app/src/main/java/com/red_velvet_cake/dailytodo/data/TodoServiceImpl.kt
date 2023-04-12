package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.red_velvet_cake.dailytodo.domain.TodoService
import okhttp3.*
import java.io.IOException

class TodoServiceImpl: TodoService {
    // token just test
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjJkZTExZTYyLWQ3ZDQtNGQzZS04ODlhLWQ0YjlkN2RmMjBmMSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMzgyNjQzfQ.VQGGXmQv0OgvIlv35Mv6aLTrK59KZmlAaRvR9rYy2yQ"
    val client = OkHttpClient()
    override fun getAllPersonalTodos(){
        Log.i("iii" , "make request")
        val request = Request.Builder().url("https://team-todo-62dmq.ondigitalocean.app/todo/personal").header("Authorization","Bearer $token" ) .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i("iii" , "fail ${e.message}")

            }
            override fun onResponse(call: Call, response: Response) {
                Log.i("iii" , " response ${response.body?.string().toString()}")
            }
        })
    }

}