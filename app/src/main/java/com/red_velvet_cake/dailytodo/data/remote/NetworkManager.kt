package com.red_velvet_cake.dailytodo.data.remote

import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


class NetworkManager {

    companion object {

        private const val DEFAULT_TIMEOUT = 10L // seconds

        fun hasInternetConnection(
            onSuccess: (Boolean) -> Unit,
            onFailure: (IOException) -> Unit,
            timeout: Long = DEFAULT_TIMEOUT,
        ) {
            val client = OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build()

            val request = Request.Builder()
                .url("https://www.google.com")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onFailure(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    onSuccess(response.isSuccessful)
                }
            })
        }
    }
}
