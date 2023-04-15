package com.red_velvet_cake.dailytodo.data.remote

import com.red_velvet_cake.dailytodo.data.local.LocalData
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TodoServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, "Bearer " + LocalData[HEADER_AUTHORIZATION])
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}