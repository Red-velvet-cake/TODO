package com.red_velvet_cake.dailytodo.data.remote

import android.util.Base64
import android.util.Log
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import com.red_velvet_cake.dailytodo.data.local.LocalData
import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import com.red_velvet_cake.dailytodo.data.model.*
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
import org.json.JSONObject

class TodoServiceImpl : TodoService {

    private val authOkHttpClient = AuthOkHttpClient.getInstance()

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient().newBuilder().addInterceptor(TodoServiceInterceptor())
        .addInterceptor(loggingInterceptor).build()

    private val gson = Gson()

    override fun loginUser(
        username: String,
        password: String,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (exception: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(PATH_LOGIN).build()
        val credentials = "${username}:${password}"
        val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        val authHeaderValue = "Basic $encodedCredentials"
        val request =
            Request.Builder().url(url).header(HEADER_AUTHORIZATION, authHeaderValue).build()

        authOkHttpClient.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onLoginUserFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { responseBody ->
                    val loginResponse = Gson().fromJson(responseBody, LoginResponse::class.java)
                    if (loginResponse.isSuccess) {
                        Log.d("Token ", "onResponse: ${loginResponse.loginResponseBody.token}")
                        SharedPrefs.token = loginResponse.loginResponseBody.token
                        onLoginUserSuccess(loginResponse)
                    } else {
                        val message = loginResponse.message
                        onLoginUserFailure(IOException(message))
                    }
                }
            }
        })
    }

    override fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onCreateTeamTodoSuccess: (CreateTodoTeamResponse) -> Unit,
        onCreateTeamTodoFailure: (IOException) -> Unit
    ) {
        val requestBody = FormBody.Builder().add(TITLE, title).add(DESCRIPTION, description)
            .add(ASSIGNEE, assignee).build()

        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT).build()

        val request = Request.Builder().url(url).post(requestBody).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onCreateTeamTodoFailure(e)

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
        onCreatePersonalTodoFailure: (e: IOException) -> Unit
    ) {

        val requestBody = FormBody.Builder().add(TITLE, title).add(DESCRIPTION, description).build()

        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL).build()

        val request = Request.Builder().url(url).put(requestBody).build()

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
        onGetAllPersonalTodoFailure: (e: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL).build()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllPersonalTodoFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
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
        onUpdateTeamTodoStatusSuccess: (updateTeamStatusResponse: UpdateTeamTodoStatusResponse) -> Unit,
        onUpdateTeamTodoStatusFailure: (e: IOException) -> Unit
    ) {

        val requestBody =
            FormBody.Builder().add(PARAM_ID, todoId).add(PARAM_STATUS, newTodoStatus.toString())
                .build()

        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT).build()

        val request = Request.Builder().url(url).put(requestBody).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onUpdateTeamTodoStatusFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string().toString()
                val result = gson.fromJson(body, UpdateTeamTodoStatusResponse::class.java)
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

        val requestBody =
            FormBody.Builder().add(PARAM_STATUS, todoId).add(PARAM_ID, newTodoStatus.toString())
                .build()

        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(PATH_PERSONAL).build()

        val request = Request.Builder().url(url).put(requestBody).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onUpdatePersonalTodoStatusFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = gson.fromJson(it, UpdatePersonalStatusResponse::class.java)
                    onUpdatePersonalTodoStatusSuccess(result)
                }
            }
        })
    }

    override fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (GetAllTeamTodosResponse) -> Unit,
        onGetAllTeamTodosFailure: (IOException) -> Unit,
    ) {
        val url = HttpUrl.Builder().scheme(SCHEME).host(HOST).addPathSegment(TO_DO_PATH_SEGMENT)
            .addPathSegment(TEAM_PATH_SEGMENT).build()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onGetAllTeamTodosFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val result = gson.fromJson(
                    response.body?.string().toString(), GetAllTeamTodosResponse::class.java
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
        val formBody =
            FormBody.Builder()
                .add(USERNAME, userName)
                .add(PASSWORD, password)
                .add(TEAM_ID, teamId)
                .build()

        val url = HttpUrl
            .Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(REGISTER_PATH)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        authOkHttpClient.client.newCall(request)
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
        private const val TO_DO_PATH_SEGMENT = "todo"
        private const val TEAM_PATH_SEGMENT = "team"
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