package com.red_velvet_cake.dailytodo.data.remote.auth


import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.util.HttpMethod
import com.red_velvet_cake.dailytodo.data.remote.util.buildRequest
import com.red_velvet_cake.dailytodo.utils.Constants.HOST_NAME
import com.red_velvet_cake.dailytodo.utils.Constants.SCHEME
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
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
        onFailure: (exception: Exception) -> Unit
    ) {
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST_NAME)
            .addPathSegment(LOGIN_PATH)
            .build()

        val authHeaderValue = Credentials.basic(username, password)

        val request = Request.Builder()
            .url(url)
            .header(AUTHORIZATION_HEADER, authHeaderValue)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, ioException: IOException) {
                onFailure(ioException)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    response.body?.string()?.let { responseBody ->
                        val loginResponse = Gson().fromJson(responseBody, LoginResponse::class.java)
                        onSuccess(loginResponse)
                    }
                } catch (exception: Exception) {
                    onFailure(exception)
                }
            }
        })
    }

    override fun registerAccount(
        username: String,
        password: String,
        teamId: String,
        onSuccess: (response: RegisterAccountResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    ) {
        val apiRequest = buildRequest(
            HOST_NAME,
            SIGNUP_PATH,
            HttpMethod.POST,
            USERNAME to username,
            PASSWORD to password,
            TEAM_ID to teamId
        )

        client.newCall(apiRequest)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, ioException: IOException) {
                    onFailure(ioException)
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        response.body?.string().let {
                            val result = gson.fromJson(it, RegisterAccountResponse::class.java)
                            onSuccess(result)
                        }

                    } catch (exception: Exception) {
                        onFailure(exception)
                    }
                }
            })
    }


    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val TEAM_ID = "teamId"
        private const val SIGNUP_PATH = "signup"
        private const val LOGIN_PATH = "login"
        private const val AUTHORIZATION_HEADER = "Authorization"
    }
}