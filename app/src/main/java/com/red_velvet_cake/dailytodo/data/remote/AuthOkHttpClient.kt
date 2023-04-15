package com.red_velvet_cake.dailytodo.data.remote

import com.red_velvet_cake.dailytodo.utils.Constants.HOST
import com.red_velvet_cake.dailytodo.utils.Constants.SCHEME
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class AuthOkHttpClient private constructor() {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpUrlBuilder = HttpUrl.Builder()
        .scheme(SCHEME)
        .host(HOST)

    val client: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(logging)
        .build()

    companion object {
        private var instance: AuthOkHttpClient? = null

        fun getInstance(): AuthOkHttpClient {
            if (instance == null) {
                instance = AuthOkHttpClient()
            }
            return instance!!
        }
    }
}