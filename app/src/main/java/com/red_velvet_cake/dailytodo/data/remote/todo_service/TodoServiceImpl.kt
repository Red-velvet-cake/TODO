package com.red_velvet_cake.dailytodo.data.remote.todo_service

import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.utils.Constants.HOST
import com.red_velvet_cake.dailytodo.utils.Constants.SCHEME
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException

class TodoServiceImpl : TodoService {
    private val gson = Gson()

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(AuthorizationInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    override fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onCreateTeamTodoSuccess: (CreateTodoTeamResponse) -> Unit,
        onCreateTeamTodoFailure: (errorMessage: String) -> Unit
    ) {
        val requestBody = FormBody.Builder()
            .add(TITLE, title)
            .add(DESCRIPTION, description)
            .add(ASSIGNEE, assignee)
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onCreateTeamTodoFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = gson.fromJson(it, CreateTodoTeamResponse::class.java)
                    onCreateTeamTodoSuccess(result)
                }
            }
        })
    }

    override fun createPersonalTodo(
        title: String,
        description: String,
        onCreatePersonalTodoSuccess: (CreateTodoPersonalResponse) -> Unit,
        onCreatePersonalTodoFailure: (errorMessage: String) -> Unit
    ) {

        val requestBody = FormBody.Builder()
            .add(TITLE, title)
            .add(DESCRIPTION, description)
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL)
            .build()

        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onCreatePersonalTodoFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = gson.fromJson(it, CreateTodoPersonalResponse::class.java)
                    onCreatePersonalTodoSuccess(result)
                }
            }

        })
    }

    override fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (errorMessage: String) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL)
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllPersonalTodoFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 401) {
                    onGetAllPersonalTodoFailure(response.message)
                    return
                }
                response.body?.string().let {
                    val result = gson.fromJson(it, GetAllPersonalTodosResponse::class.java)
                    onGetAllPersonalTodosSuccess(result)
                }
            }
        })
    }

    override fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusFailure: (errorMessage: String) -> Unit
    ) {

        val requestBody =
            FormBody.Builder()
                .add(PARAM_ID, todoId)
                .add(PARAM_STATUS, newTodoStatus.toString())
                .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT)
            .build()

        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onUpdateTeamTodoStatusFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {}
        })

    }

    override fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusFailure: (errorMessage: String) -> Unit
    ) {
        val requestBody =
            FormBody.Builder()
                .add(PARAM_ID, todoId)
                .add(PARAM_STATUS, newTodoStatus.toString())
                .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PERSONAL_PATH_SEGMENT)
            .build()

        val request = Request.Builder()
            .url(url)
            .put(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onUpdatePersonalTodoStatusFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {}
        })
    }

    override fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (GetAllTeamTodosResponse) -> Unit,
        onGetAllTeamTodosFailure: (errorMessage: String) -> Unit,
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(
                TEAM_PATH_SEGMENT
            ).build()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllTeamTodosFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 401) {
                    onGetAllTeamTodosFailure(response.message)
                    return
                }
                val result = gson.fromJson(
                    response.body?.string().toString(), GetAllTeamTodosResponse::class.java
                )
                onGetAllTeamTodosSuccess(result)
            }
        })
    }

    companion object {
        private const val PARAM_ID = "id"
        private const val PARAM_STATUS = "status"
        private const val PATH_PERSONAL = "personal"
        private const val TO_DO_PATH_SEGMENT = "todo"
        private const val TEAM_PATH_SEGMENT = "team"
        private const val PERSONAL_PATH_SEGMENT = "personal"
        private const val REGISTER_PATH = "signup"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val TEAM_ID = "teamId"
        private const val PATH_LOGIN = "login"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val ASSIGNEE = "assignee"
    }
}