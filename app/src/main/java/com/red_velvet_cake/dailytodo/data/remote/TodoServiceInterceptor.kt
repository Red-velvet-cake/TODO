package com.red_velvet_cake.dailytodo.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TodoServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader(
                HEADER_AUTHORIZATION,
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6Ijk4YWRiZWJkLTg2YmQtNDg3Yy1hYjI1LWVlY2IzOGQxZjIxZSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxOTE2ODI0fQ.gj5DewPhqiZ5OW6x52tEJdC4ZnLGOjKLMOanYMMARJs"
            )
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}