package com.red_velvet_cake.dailytodo.data.remote.todo_service

import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException
import com.red_velvet_cake.dailytodo.data.remote.util.HttpMethod
import com.red_velvet_cake.dailytodo.data.remote.util.buildRequest
import com.red_velvet_cake.dailytodo.utils.Constants.HOST_NAME
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
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
        onSuccess: (CreateTodoTeamResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit
    ) {
        val apiRequest = buildRequest(
            HOST_NAME,
            TEAM_TODO_PATH_SEGMENT,
            HttpMethod.POST,
            TITLE to title,
            DESCRIPTION to description,
            ASSIGNEE to assignee
        )

        client.newCall(apiRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    response.body?.string().let {
                        val result = gson.fromJson(it, CreateTodoTeamResponse::class.java)
                        onSuccess(result)
                    }
                } catch (authorizationException: CustomException.UnauthorizedUserException) {
                    onFailure(authorizationException)
                }
            }
        })
    }

    override fun createPersonalTodo(
        title: String,
        description: String,
        onSuccess: (CreateTodoPersonalResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit
    ) {
        val apiRequest = buildRequest(
            HOST_NAME,
            PERSONAL_TODO_PATH_SEGMENT,
            HttpMethod.POST,
            TITLE to title,
            DESCRIPTION to description
        )

        client.newCall(apiRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    response.body?.string().let {
                        val result = gson.fromJson(it, CreateTodoPersonalResponse::class.java)
                        onSuccess(result)
                    }

                } catch (authorizationException: CustomException.UnauthorizedUserException) {
                    onFailure(authorizationException)
                }
            }
        })
    }

    override fun getAllPersonalTodos(
        onSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit
    ) {

        val apiRequest = buildRequest(
            HOST_NAME,
            PERSONAL_TODO_PATH_SEGMENT,
            HttpMethod.GET
        )

        client.newCall(apiRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    response.body?.string().let {
                        val result = gson.fromJson(it, GetAllPersonalTodosResponse::class.java)
                        onSuccess(result)
                    }
                } catch (authorizationException: CustomException.UnauthorizedUserException) {
                    onFailure(authorizationException)
                }
            }
        })
    }

    override fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onFailure: (exception: Exception) -> Unit
    ) {

        val apiRequest = buildRequest(
            HOST_NAME,
            TEAM_TODO_PATH_SEGMENT,
            HttpMethod.PUT,
            PARAM_ID to todoId,
            PARAM_STATUS to newTodoStatus.toString()
        )

        client.newCall(apiRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    response.body
                } catch (authorizationException: CustomException.UnauthorizedUserException) {
                    onFailure(authorizationException)
                }
            }
        })

    }

    override fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onFailure: (exception: Exception) -> Unit
    ) {
        val apiRequest = buildRequest(
            HOST_NAME,
            PERSONAL_TODO_PATH_SEGMENT,
            HttpMethod.PUT,
            PARAM_ID to todoId,
            PARAM_STATUS to newTodoStatus.toString()
        )

        client.newCall(apiRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    response.body
                } catch (authorizationException: CustomException.UnauthorizedUserException) {
                    onFailure(authorizationException)
                }
            }
        })
    }

    override fun getAllTeamTodos(
        onSuccess: (GetAllTeamTodosResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    ) {

        val apiRequest = buildRequest(
            HOST_NAME,
            TEAM_TODO_PATH_SEGMENT,
            HttpMethod.GET
        )

        client.newCall(apiRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val result = gson.fromJson(
                        response.body?.string().toString(), GetAllTeamTodosResponse::class.java
                    )
                    onSuccess(result)
                } catch (authorizationException: CustomException.UnauthorizedUserException) {
                    onFailure(authorizationException)
                }
            }
        })
    }

    companion object {
        private const val TEAM_TODO_PATH_SEGMENT = "todo/team"
        private const val PERSONAL_TODO_PATH_SEGMENT = "todo/personal"
        private const val PARAM_ID = "id"
        private const val PARAM_STATUS = "status"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val ASSIGNEE = "assignee"
    }
}