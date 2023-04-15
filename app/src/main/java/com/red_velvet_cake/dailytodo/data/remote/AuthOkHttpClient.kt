package com.red_velvet_cake.dailytodo.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class AuthOkHttpClient private constructor() {
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
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