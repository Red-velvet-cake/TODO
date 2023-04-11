package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.red_velvet_cake.dailytodo.domain.TodoService
import okhttp3.*
import okhttp3.internal.http2.Http2
import java.io.IOException
import kotlin.math.log


class TodoServiceImpl: TodoService {
     private val client=OkHttpClient()
     val token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6ImJiYjg5ZDE3LTEwOWItNDA3Ny05OTQ3LWQ0MWY3OTgzM2RjNCIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNDA2NTQ3fQ.3a974W_9jo72z89deuVIbf6w3rmCILIxg_Z7rPzFOXY"
     override fun creatTeamTodo(title: String, description: String, assignee: String) {
          val url=HttpUrl.Builder().
          scheme("https").host("team-todo-62dmq.ondigitalocean.app").addPathSegment("todo").addPathSegment("team")
               .addQueryParameter("title",title)
               .addQueryParameter("description",description)
               .addQueryParameter("assignee",assignee)
               .build()
          val request=Request.Builder().url(url).addHeader("Authorization", token).build()
          client.newCall(request).enqueue(object :Callback{
               override fun onFailure(call: Call, e: IOException) {
                    Log.i("todo",e.message.toString())
               }

               override fun onResponse(call: Call, response: Response) {
                    Log.i("todo",response.body?.string().toString())

               }

          })

     }


}