package com.red_velvet_cake.dailytodo.data

import com.google.gson.Gson
import com.red_velvet_cake.dailytodo.domain.GetAllPersonalTodosResponse
import okhttp3.*
import java.io.IOException

class TodoServiceImpl: TodoService {
    val client = OkHttpClient()
    override fun getAllPersonalTodos(
        onSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onFailure: (e: IOException) -> Unit
    ){
        val url = HttpUrl.Builder()
            .scheme(SCHEME_HTTPS)
            .host(HOST)
            .addPathSegment(PATH_TODO)
            .addPathSegment(PATH_PERSONAL)
            .build()
        val request = Request.Builder().url(url).header(HEADER_AUTHORIZATION,"Bearer $AUTH_TOKEN" ) .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)

            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let {
                    val result = Gson().fromJson(it , GetAllPersonalTodosResponse::class.java)
                    onSuccess(result)
                }
            }
        })
    }
    companion object {
        private const val SCHEME_HTTPS = "https"
        private const val HOST = "team-todo-62dmq.ondigitalocean.app"
        private const val PATH_TODO = "todo"
        private const val PATH_PERSONAL = "personal"
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTH_TOKEN =
         "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjJkZTExZTYyLWQ3ZDQtNGQzZS04ODlhLWQ0YjlkN2RmMjBmMSIsInRlYW1JZCI6IjAxYThhOTg4LTQ0NjItNDNhNi1hOThhLTE2MjY4NzNmYTc4NyIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMzgyNjQzfQ.VQGGXmQv0OgvIlv35Mv6aLTrK59KZmlAaRvR9rYy2yQ"
    }

}