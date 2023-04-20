package com.red_velvet_cake.dailytodo.data.remote.todo_service

import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.utils.Constants.HOST
import okhttp3.*
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
        onCreateTeamTodoSuccess: (CreateTodoTeamResponse) -> Unit,
        onCreateTeamTodoFailure: (errorMessage: String) -> Unit
    ) {
        val apiRequest = buildRequest(
            HOST,
            "todo/team",
            HttpMethod.POST,
            TITLE to title,
            DESCRIPTION to description,
            ASSIGNEE to assignee
        )

        client.newCall(apiRequest).enqueue(object : Callback {
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
        val apiResuest = buildRequest(
            HOST,
            "todo/personal",
            HttpMethod.POST,
            "title" to title,
            "description" to description
        )

        client.newCall(apiResuest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onCreatePersonalTodoFailure(e.message.toString())
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

        val apiRequest = buildRequest(
            HOST,
            "todo/personal",
            HttpMethod.GET
        )

        client.newCall(apiRequest).enqueue(object : Callback {
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

        val apiRequest = buildRequest(
            HOST,
            "todo/team",
            HttpMethod.PUT,
            PARAM_ID to todoId,
            PARAM_STATUS to newTodoStatus.toString()
        )

        client.newCall(apiRequest).enqueue(object : Callback {
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
        val apiRequest = buildRequest(
            HOST,
            "todo/team",
            HttpMethod.PUT,
            PARAM_ID to todoId,
            PARAM_STATUS to newTodoStatus.toString()
        )

        client.newCall(apiRequest).enqueue(object : Callback {
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

        val apiRequest = buildRequest(
            HOST,
            "todo/team",
            HttpMethod.GET
        )

        client.newCall(apiRequest).enqueue(object : Callback {
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
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val ASSIGNEE = "assignee"
    }
}