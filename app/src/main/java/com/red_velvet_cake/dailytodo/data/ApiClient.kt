package com.red_velvet_cake.dailytodo.data

import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.data.enum.HttpMethodType
import com.red_velvet_cake.dailytodo.data.remote.dto.ApiResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.lang.reflect.Type

class ApiClient private constructor() {
    private var client: OkHttpClient
    private var baseUrl: HttpUrl
    private var gson: Gson

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

        baseUrl = HttpUrl.Builder()
            .scheme("https")
            .host("team-todo-62dmq.ondigitalocean.app")
            .build()

        gson = Gson()
    }

    fun <T> makeRequest(
        path: String,
        responseType: Type,
        httpMethod: HttpMethodType,
        callback: (response: ApiResponse<T>) -> Unit
    ) {
        val request = Request.Builder()
            .url("$baseUrl$path")
            .method(httpMethod.name, null)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) = throw e

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val result = body?.let { gson.fromJson<T>(it, responseType) }
                    val apiResponse = ApiResponse(result, response.message, true)
                    callback(apiResponse)
                }
            }
        })
    }

    companion object {
        private var INSTANCE: ApiClient? = null

        fun getInstance(): ApiClient {
            if (INSTANCE == null) {
                synchronized(ApiClient::class.java) {
                    INSTANCE = ApiClient()
                }
            }
            return INSTANCE!!
        }
    }
}