package com.red_velvet_cake.dailytodo.data.remote.util

import com.red_velvet_cake.dailytodo.utils.Constants
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.Request

enum class HttpMethod {
    GET, POST, PUT
}

fun buildRequest(
    host: String, path: String, httpMethod: HttpMethod, vararg fields: Pair<String, String>
): Request {

    val url = HttpUrl.Builder().scheme(Constants.SCHEME).host(host).addPathSegments(path).build()

    val request = Request.Builder().url(url)

    val formBody = FormBody.Builder()

    return when (httpMethod) {
        HttpMethod.GET -> {
            request.build()
        }

        HttpMethod.POST -> {
            fields.forEach {
                formBody.add(it.first, it.second)
            }
            request.post(formBody.build())
            request.build()
        }

        HttpMethod.PUT -> {
            fields.forEach {
                formBody.add(it.first, it.second)
            }
            request.put(formBody.build())
            request.build()
        }
    }
}