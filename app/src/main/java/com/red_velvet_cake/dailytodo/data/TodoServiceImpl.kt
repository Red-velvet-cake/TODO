package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.red_velvet_cake.dailytodo.domain.TodoService
import okhttp3.*
import java.io.IOException

class TodoServiceImpl: TodoService {
    private val client= OkHttpClient()
    override fun getTeamToDo() {
         val token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjY3NzVlYWJhLTEwMzktNGViMi05ODA5LTI5MjUzNWRmMDM1MCIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMzAyMzM4fQ.9N6NeI1h854Ga3tVjphbU1n-fvQayyv64hRk2QUlmBQ"
            val url= HttpUrl.Builder().
            scheme("https").
            host("team-todo-62dmq.ondigitalocean.app")
                .addPathSegment("todo")
                .addPathSegment("team").build()
            val request= Request.Builder().url(url).addHeader("Authorization",token).build()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("token",e.message.toString())
                }
                override fun onResponse(call: Call, response: Response) {
                  Log.i("token",response.body?.string().toString())
                }
            })

    }
    }