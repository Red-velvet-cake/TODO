package com.red_velvet_cake.dailytodo.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class TodoServiceInterceptor: Interceptor {
    companion object{
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request
            .newBuilder()
            .addHeader(AUTHORIZATION_HEADER, "bearer API_TOKEN_FROM_SHARED_PREF")
            .build()

        return chain.proceed(request)
    }
}