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

    private val client = OkHttpClient()
    override fun updatePersonalTodoStatus(
        userId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (exception: IOException) -> Unit
    ) {

        val requestBody = FormBody.Builder().add(PARAM_STATUS, userId)
            .add(PARAM_ID, newTodoStatus.toString())
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(PATH_TODO)
            .addPathSegment(PATH_PERSONAL)
            .build()

        val request = Request.Builder()
            .url(url).put(requestBody).addHeader(
                HEADER_AUTHORIZATION,
                "bearer $AUTH_TOKEN"
            ).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onUpdatePersonalTodoStatusFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = Gson().fromJson(it, UpdatePersonalStatusResponse::class.java)
                    onUpdatePersonalTodoStatusSuccess(result)
                }

            }
        })
    }


    companion object {
        private const val PARAM_ID = "id"
        private const val PARAM_STATUS = "status"
        private const val SCHEME_HTTPS = "https"
        private const val HOST = "team-todo-62dmq.ondigitalocean.app"
        private const val PATH_TODO = "todo"
        private const val PATH_PERSONAL = "personal"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTH_TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6Ijk4YWRiZWJkLTg2YmQtNDg3Yy1hYjI1LWVlY2IzOGQxZjIxZSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNDAyMjc3fQ.iCNnBgrYNOZSc707UD-oY8h9PsW0WNyocAmD7-hMucM"
    }

}