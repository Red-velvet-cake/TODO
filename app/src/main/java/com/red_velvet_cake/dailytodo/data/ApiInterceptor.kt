package com.red_velvet_cake.dailytodo.data

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    companion object {
        private const val CONTENT_TYPE_HEADER = "Content-Type"
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request
            .newBuilder()
            .addHeader(CONTENT_TYPE_HEADER, "application/json")
            .addHeader(AUTHORIZATION_HEADER, "bearer TOKEN")
            .build()

        return chain.proceed(request)
    }
}
