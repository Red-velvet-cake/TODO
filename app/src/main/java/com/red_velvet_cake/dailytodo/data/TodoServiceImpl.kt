package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.model.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.jetbrains.annotations.NotNull

class TodoServiceImpl : TodoService {

    private val client = OkHttpClient()
    
    override fun createPersonalTodo(
        todo: TODO,
        onCreatePersonalTodoSuccess: (Boolean) -> Unit,
        onCreatePersonalTodoFailure: (e: IOException) -> Unit
    ) {

        val requestBody = FormBody.Builder()
            .add(TITLE, todo.title)
            .add(DESCRIPTION, todo.description)
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL)
            .build()

        val request = Request
            .Builder()
            .url(url)
            .addHeader(HEADER_AUTHORIZATION, "Bearer $AUTH_TOKEN")
            .put(requestBody)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onCreatePersonalTodoFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                onCreatePersonalTodoSuccess(response.isSuccessful)
            }

        })
    }

    override fun createTeamTodo(
        todo: TODO,
        onCreateTeamTodoSuccess: (Boolean) -> Unit,
        onCreateTeamTodoFailure: (e: IOException) -> Unit
    ) {
        val requestBody = FormBody.Builder()
            .add(TITLE, todo.title)
            .add(DESCRIPTION, todo.description)
            .add(ASSIGNEE, todo.assignee.toString())
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL)
            .build()

        val request = Request
            .Builder()
            .url(url)
            .addHeader(HEADER_AUTHORIZATION, "Bearer $AUTH_TOKEN")
            .put(requestBody)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onCreateTeamTodoFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                onCreateTeamTodoSuccess(response.isSuccessful)
            }

        })
    }

    override fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (e: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL)
            .build()

        val request =
            Request.Builder().url(url).header(HEADER_AUTHORIZATION, "Bearer $AUTH_TOKEN").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllPersonalTodoFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = Gson().fromJson(it, GetAllPersonalTodosResponse::class.java)
                    onGetAllPersonalTodosSuccess(result)
                }
            }
        })
    }

    override fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusSuccess: (updateTeamStatusResponse: UpdateTeamTodoStatusResponse) -> Unit,
        onUpdateTeamTodoStatusFailure: (e: IOException) -> Unit
    ) {

        val requestBody = FormBody.Builder()
            .add(PARAM_ID, todoId)
            .add(PARAM_STATUS, newTodoStatus.toString())
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .build()

        val request = Request
            .Builder()
            .url(url)
            .addHeader(HEADER_AUTHORIZATION, "Bearer $AUTH_TOKEN")
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onUpdateTeamTodoStatusFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string().toString()
                val result = Gson().fromJson(body, UpdateTeamTodoStatusResponse::class.java)
                onUpdateTeamTodoStatusSuccess(result)
            }

        })

    }

    override fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (exception: IOException) -> Unit
    ) {

        val requestBody = FormBody.Builder().add(PARAM_STATUS, todoId)
            .add(PARAM_ID, newTodoStatus.toString())
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
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

    override fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (GetAllTeamTodosResponse) -> Unit,
        onGetAllTeamTodosFailure: (IOException) -> Unit,
    ) {
        val url = HttpUrl
            .Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT)
            .build()

        val request = Request
            .Builder()
            .url(url)
            .addHeader(HEADER_AUTHORIZATION, AUTH_TOKEN)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllTeamTodosFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val result =
                    Gson().fromJson(
                        response.body?.string().toString(),
                        GetAllTeamTodosResponse::class.java
                    )
                Log.i("iii", result.value[0].creationTime)
                onGetAllTeamTodosSuccess(result)
            }
        })
    }

    companion object {
        private const val PARAM_ID = "id"
        private const val PARAM_STATUS = "status"
        private const val SCHEME_HTTPS = "https"
        private const val HOST = "team-todo-62dmq.ondigitalocean.app"
        private const val PATH_PERSONAL = "personal"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTH_TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6Ijk4YWRiZWJkLTg2YmQtNDg3Yy1hYjI1LWVlY2IzOGQxZjIxZSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNDAyMjc3fQ.iCNnBgrYNOZSc707UD-oY8h9PsW0WNyocAmD7-hMucM"
        private const val TO_DO_PATH_SEGMENT = "todo"
        private const val TEAM_PATH_SEGMENT = "team"

        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val ASSIGNEE = "assignee"
    }
}