package com.red_velvet_cake.dailytodo.data.remote.service

import com.red_velvet_cake.dailytodo.data.remote.dto.ApiResponse
import com.red_velvet_cake.dailytodo.data.remote.dto.RegisterDto

interface TodoService {

    fun register(
        userName: String,
        password: String,
        teamId: String,
        callback: (ApiResponse<RegisterDto>) -> Unit
    )
}