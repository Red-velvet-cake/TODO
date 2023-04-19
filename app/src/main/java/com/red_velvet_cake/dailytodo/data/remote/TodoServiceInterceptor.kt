package com.red_velvet_cake.dailytodo.data.remote

import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class TodoServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = SharedPrefs.token
            val authHeaderValue = "Bearer $token"
            val newRequest: Request = request.newBuilder()
                .addHeader(HEADER_AUTHORIZATION, authHeaderValue)
                .build()

            return chain.proceed(newRequest)

    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}