package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.domain.TodoService
import okhttp3.*
import java.io.IOException

class TodoServiceImpl: TodoService {
    private val client= OkHttpClient()
    override fun getTeamToDo(presentTeamTodo:(TeamTodos)->Unit) {
         val token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjY3NzVlYWJhLTEwMzktNGViMi05ODA5LTI5MjUzNWRmMDM1MCIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMzAyMzM4fQ.9N6NeI1h854Ga3tVjphbU1n-fvQayyv64hRk2QUlmBQ"
         val pathSegments= listOf("todo","team")
        val scheme="https"
            val url= HttpUrl.Builder().
            scheme(scheme).
            host(HOST_NAME)
                .addPathSegment(pathSegments[0])
                .addPathSegment(pathSegments[1]).build()
            val request= Request.Builder().url(url).addHeader(AUTH,token).build()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("token",e.message.toString())
                }
                override fun onResponse(call: Call, response: Response) {

                 val result=Gson().fromJson(response.body?.string().toString(),TeamTodos::class.java)
                   presentTeamTodo(result)
                }
            })

    }
    companion object{
        const val HOST_NAME="team-todo-62dmq.ondigitalocean.app"
        const val AUTH="Authorization"
    }
    }