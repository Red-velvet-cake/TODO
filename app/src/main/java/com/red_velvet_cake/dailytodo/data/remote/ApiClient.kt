package com.red_velvet_cake.dailytodo.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.red_velvet_cake.dailytodo.data.remote.dto.ApiResponse
import com.red_velvet_cake.dailytodo.data.remote.enum.HttpMethodType
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class ApiClient private constructor() {
    private var client: OkHttpClient
    private var baseUrl: HttpUrl
    private var gson: Gson

    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        baseUrl = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .build()

        gson = Gson()
    }

    fun <T> makeRequest(
        path: String,
        httpMethod: HttpMethodType,
        requestBody: RequestBody? = null,
        token: String? = null,
        callback: (response: ApiResponse<T>) -> Unit
    ) {
        val request = Request.Builder()
            .url("$baseUrl$path")
            .method(httpMethod.name, requestBody)
            .build()

        client.newBuilder()
            .addInterceptor(ApiInterceptor(token))
            .build()
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    handleOnFailure(e, callback)
                }

                override fun onResponse(call: Call, response: Response) {
                    handleOnResponse(response, callback)
                }
            })
    }

    private fun <T> handleOnFailure(e: IOException, callback: (response: ApiResponse<T>) -> Unit) {
        callback(ApiResponse(null, e.message ?: "Unknown error", false))
    }

    private fun <T> handleOnResponse(
        response: Response,
        callback: (response: ApiResponse<T>) -> Unit
    ) {
        if (!response.isSuccessful) {
            return callback(ApiResponse(null, response.message, false))
        } else {
            val body = response.body?.string()
            val responseType = object : TypeToken<ApiResponse<T>>() {}.type
            val result = body?.let { gson.fromJson<T>(it, responseType) }
            val apiResponse = ApiResponse(result, response.message, true)
            callback(apiResponse)
        }
    }

    companion object {
        private const val HOST = "team-todo-62dmq.ondigitalocean.app"
        private const val SCHEME = "https"
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