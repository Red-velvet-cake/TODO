package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.model.GetAllPersonalTodosResponse
import okhttp3.*
import java.io.IOException
class TodoServiceImpl: TodoService {
    override fun getAllPersonalTodos(
        onSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onFailure: (e: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(PATH_TODO)
            .addPathSegment(PATH_PERSONAL)
            .build()
        val request =
            Request.Builder().url(url).header(HEADER_AUTHORIZATION, "Bearer $AUTH_TOKEN").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)

            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = Gson().fromJson(it, GetAllPersonalTodosResponse::class.java)
                    onSuccess(result)
                }
            }
        })
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