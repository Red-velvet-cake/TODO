package com.red_velvet_cake.dailytodo.data.remote.todo_service

import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = SharedPrefs.token
        val authHeaderValue = "$BEARER $token"
        val newRequest: Request = request.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, authHeaderValue)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
    }
}