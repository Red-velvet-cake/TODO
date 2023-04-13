package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.data.model.login.LoginRequest
import com.red_velvet_cake.dailytodo.data.model.login.LoginResponse
import com.red_velvet_cake.dailytodo.utils.Constants.HOST
import com.red_velvet_cake.dailytodo.utils.Constants.SCHEME
import com.red_velvet_cake.dailytodo.data.model.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class TodoServiceImpl : TodoService {
    private val authOkHttpClient = AuthOkHttpClient.getInstance()
    private val client = OkHttpClient()
    private val gson = Gson()

    override fun loginUser(
        loginRequest: LoginRequest,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (exception: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(PATH_LOGIN)
            .build()

        val requestBody = FormBody.Builder()
            .add(PARAM_USERNAME, loginRequest.username)
            .add(PARAM_PASSWORD, loginRequest.password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onLoginUserFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val result = Gson().fromJson(it, LoginResponse::class.java)
                    onLoginUserSuccess(result)
                }
            }
        })
    }

    override fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onCreateTeamTodoSuccess: (Response) -> Unit,
        onCreateTeamTodoFailure: (IOException) -> Unit
    ) {
        val requestBody =FormBody.Builder().add("title", title)
            .add("description",description)
            .add("assignee",assignee).build()

        val url=HttpUrl.Builder().
        scheme("https")
            .host("team-todo-62dmq.ondigitalocean.app")
            .addPathSegment("todo")
            .addPathSegment("team")
            .addQueryParameter("title",title)
            .addQueryParameter("description",description)
            .addQueryParameter("assignee",assignee)
            .build()

        val request=Request.Builder().url(url).addHeader("Authorization", token)
            .post(requestBody).build()

        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i("todo",e.message.toString())
                onCreateTeamTodoFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("todo",response.body?.string().toString())
                onCreateTeamTodoSuccess(response)
            }
        })
    }

    override fun createPersonalTodo(
        personalTodoRequest: PersonalTODORequest,
        onCreatePersonalTodoSuccess: (Boolean) -> Unit,
        onCreatePersonalTodoFailure: (e: IOException) -> Unit
    ) {

        val requestBody = FormBody.Builder()
            .add(TITLE, personalTodoRequest.title)
            .add(DESCRIPTION, personalTodoRequest.description)
            .build()

        val url = HttpUrl.Builder()
            .scheme(SCHEME)
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

    override fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (e: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
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
            .scheme(SCHEME)
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
            .scheme(SCHEME)
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
            .scheme(SCHEME)
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

    override fun registerAccount(
        userName: String,
        password: String,
        teamId: String,
        onRegisterAccountSuccess: (registerAccountResponse: RegisterAccountResponse) -> Unit,
        onRegisterAccountFailure: (e: IOException) -> Unit
    ) {
        val formBody = FormBody.Builder()
            .add(USERNAME, userName)
            .add(PASSWORD, password)
            .add(TEAM_ID, teamId)
            .build()

        val url = authOkHttpClient.httpUrlBuilder
            .addPathSegment(REGISTER_PATH)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        authOkHttpClient.client
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onRegisterAccountFailure(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val result = gson.fromJson(body, RegisterAccountResponse::class.java)
                    onRegisterAccountSuccess(result)
                }
            })
    }

    companion object {
        private const val PARAM_ID = "id"
        private const val PARAM_STATUS = "status"
        private const val PATH_PERSONAL = "personal"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTH_TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6Ijk4YWRiZWJkLTg2YmQtNDg3Yy1hYjI1LWVlY2IzOGQxZjIxZSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNDAyMjc3fQ.iCNnBgrYNOZSc707UD-oY8h9PsW0WNyocAmD7-hMucM"
        private const val TO_DO_PATH_SEGMENT = "todo"
        private const val TEAM_PATH_SEGMENT = "team"
        private const val REGISTER_PATH = "signup"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val TEAM_ID = "teamId"
        private const val PARAM_USERNAME = "username"
        private const val PARAM_PASSWORD = "password"
        private const val PATH_LOGIN = "login"

        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val ASSIGNEE = "assignee"
    }
}