package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.domain.TodoService
import com.red_velvet_cake.dailytodo.domain.model.UpdateTeamTodoResponse
import okhttp3.*
import java.io.IOException

class TodoServiceImpl: TodoService {

    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6ImFhMzg0NTllLTViOTktNDBjNi05YzU1LWMwZGZiMGE2MDQ0MCIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMzk1NTEyfQ.rxn7xjPVXc1-zYOfF-TeFFnq0t_nifhJ3in0hIBUQ7Y"

    private fun HttpUrlBuilder() = HttpUrl.Builder()
        .scheme(SCHEME)
        .host(HOST)
        .addPathSegment("todo")
        .addPathSegment("team")
        .build()

    override fun updateTeamTodoStatus(updateToDoStatus: (UpdateTeamTodoResponse) -> Unit) {
        val requestBody = FormBody.Builder()
            .add("id", "f6a108b9-1e8e-464b-9755-e2d4582b7c63")
            .add(STATUS, "2")
            .build()

        val url = HttpUrlBuilder()
        val client = OkHttpClient()
        val request = Request
            .Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .put(requestBody)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("fai", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string().toString()
                val result: UpdateTeamTodoResponse? =
                    Gson().fromJson(body, UpdateTeamTodoResponse::class.java)
                Log.i("resp", result.toString())
//                Log.i("respCode",response.code.toString())
                updateToDoStatus(result!!)
            }

        })

    }

    companion object {
        private const val SCHEME = "https"
        private const val HOST = "team-todo-62dmq.ondigitalocean.app"
        private const val PATH = "/todo/team"
        private const val ID = "id"
        private const val STATUS = "status"

    }

}