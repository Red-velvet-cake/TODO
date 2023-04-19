package com.red_velvet_cake.dailytodo.data.remote.auth


import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.utils.Constants.HOST
import com.red_velvet_cake.dailytodo.utils.Constants.SCHEME
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class AuthenticationServiceImpl : AuthenticationService {
    private val gson = Gson()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

    override fun loginAccount(
        username: String,
        password: String,
        onSuccess: (response: LoginResponse) -> Unit,
        onFailure: (exception: IOException) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(PATH_LOGIN)
            .build()

        val authHeaderValue = Credentials.basic(username, password)

        val request = Request.Builder()
            .url(url)
            .header(HEADER_AUTHORIZATION, authHeaderValue)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { responseBody ->
                    val loginResponse = Gson().fromJson(responseBody, LoginResponse::class.java)
                    onSuccess(loginResponse)
                }
            }
        })
    }

    override fun registerAccount(
        username: String,
        password: String,
        teamId: String,
        onSuccess: (response: RegisterAccountResponse) -> Unit,
        onFailure: (exception: IOException) -> Unit,
    ) {
        val formBody = FormBody.Builder()
            .add(USERNAME, username)
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

        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onFailure(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val result = gson.fromJson(body, RegisterAccountResponse::class.java)
                    onSuccess(result)
                }
            })
    }


    companion object {
        private const val REGISTER_PATH = "signup"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val TEAM_ID = "teamId"
        private const val PATH_LOGIN = "login"
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}