package com.red_velvet_cake.dailytodo.data.remote.service

import com.red_velvet_cake.dailytodo.data.ApiClient
import com.red_velvet_cake.dailytodo.data.enum.HttpMethodType
import com.red_velvet_cake.dailytodo.data.remote.dto.ApiResponse
import com.red_velvet_cake.dailytodo.data.remote.dto.RegisterDto

class TodoServiceImpl : TodoService {
    private val client = ApiClient.getInstance()
    override fun register(
        userName: String,
        password: String,
        callback: (ApiResponse<RegisterDto>) -> Unit
    ) {
        val path = "/api/auth/register"
        client.makeRequest<RegisterDto>(
            path,
            RegisterDto::class.java,
            HttpMethodType.POST,
        ) {
            callback(it)
        }
    }
}