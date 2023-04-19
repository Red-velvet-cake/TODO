package com.red_velvet_cake.dailytodo.data.remote

import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TodoServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = SharedPrefs.token
            val authHeaderValue = "Bearer $token"
            val newRequest: Request = request.newBuilder()
                .addHeader(
                    HEADER_AUTHORIZATION,
                    "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6ImQ0MTZiNDNlLWMyOTctNDkxNy1hYjVkLTUxNDU4Njg3ZDliZSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgyMDMyNDEyfQ.XakpRl8r3whtxCUsZFKCc3hVzuy9UsnFHhkl16CxpD8"
                )
                .build()

            return chain.proceed(newRequest)

    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}