package com.red_velvet_cake.dailytodo.data.remote.service

import com.red_velvet_cake.dailytodo.data.remote.ApiClient
import com.red_velvet_cake.dailytodo.data.remote.dto.ApiResponse
import com.red_velvet_cake.dailytodo.data.remote.dto.RegisterDto
import com.red_velvet_cake.dailytodo.data.remote.enum.HttpMethodType
import okhttp3.FormBody

class TodoServiceImpl : TodoService {
    private val client = ApiClient.getInstance()
    override fun register(
        userName: String,
        password: String,
        teamId: String,
        callback: (ApiResponse<RegisterDto>) -> Unit
    ) {
        val formBody = FormBody.Builder()
            .add(USERNAME, userName)
            .add(PASSWORD, password)
            .add(TEAM_ID, teamId)
            .build()

        client.makeRequest(
            path = REGISTER_PATH,
            httpMethod = HttpMethodType.POST,
            requestBody = formBody,
        ) {
            callback(it)
        }
    }

    companion object {
        private const val REGISTER_PATH = "signup"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val TEAM_ID = "teamId"
    }
}