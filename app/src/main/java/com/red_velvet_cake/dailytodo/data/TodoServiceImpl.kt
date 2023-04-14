package com.red_velvet_cake.dailytodo.data

import android.util.Log
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import com.red_velvet_cake.dailytodo.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.model.login.LoginRequest
import com.red_velvet_cake.dailytodo.model.login.LoginResponse
import okhttp3.*
import okio.IOException

class TodoServiceImpl : TodoService {

    private val client = OkHttpClient()
    private val authToken: String
        get() = Hawk.get<String>("auth_token") ?: ""
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
            Request.Builder().url(url).header(HEADER_AUTHORIZATION, "Bearer $authToken").build()

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
            .addHeader(HEADER_AUTHORIZATION, "Bearer $authToken")
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

    override fun loginUser(
        loginRequest: LoginRequest,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (exception: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
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
                    Hawk.put(authToken, result.token)
                    onLoginUserSuccess(result)
                }
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
                "bearer $authToken"
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
            .addHeader(HEADER_AUTHORIZATION, authToken)
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
        private const val TO_DO_PATH_SEGMENT = "todo"
        private const val TEAM_PATH_SEGMENT = "team"
        private const val PARAM_USERNAME = "username"
        private const val PARAM_PASSWORD = "password"
        private const val PATH_LOGIN = "login"
    }
}