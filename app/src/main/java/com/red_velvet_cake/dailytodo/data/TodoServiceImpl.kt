package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.AllTeamTodos
import okhttp3.*
import okio.IOException

class TodoServiceImpl : TodoService {
    private val client = OkHttpClient()
    private val token =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjY3NzVlYWJhLTEwMzktNGViMi05ODA5LTI5MjUzNWRmMDM1MCIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNDAzNjIxfQ.um2TPSGBBzZBieplYjVZvsTXjPnLOYUracapBnMA-KA"

    override fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (AllTeamTodos) -> Unit,
        onGetAllTeamTodosFailure: (IOException) -> Unit,
    ) {
        val url = HttpUrl
            .Builder()
            .scheme(SCHEME)
            .host(HOST_NAME)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT)
            .build()
        val request = Request
            .Builder()
            .url(url)
            .addHeader(AUTH, token)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllTeamTodosFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val result =
                    Gson().fromJson(response.body?.string().toString(), AllTeamTodos::class.java)
                Log.i("iii", result.value[0].creationTime)
                onGetAllTeamTodosSuccess(result)
            }
        })
    }

    companion object {
        const val HOST_NAME = "team-todo-62dmq.ondigitalocean.app"
        const val AUTH = "Authorization"
        const val TO_DO_PATH_SEGMENT = "todo"
        const val TEAM_PATH_SEGMENT = "team"
        const val SCHEME = "https"
    }
}