package com.red_velvet_cake.dailytodo.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(private val token: String?) : Interceptor {
    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val CONTENT_TYPE_HEADER = "Content-Type"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request
            .newBuilder()
            .addHeader(CONTENT_TYPE_HEADER, "application/json")
            .addHeader(AUTHORIZATION_HEADER, token ?: "")
            .build()

        return chain.proceed(request)
    }
}
