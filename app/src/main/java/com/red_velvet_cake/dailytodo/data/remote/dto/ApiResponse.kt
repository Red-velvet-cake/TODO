package com.red_velvet_cake.dailytodo.data.remote.dto

data class ApiResponse<T>(
    val value: T?,
    val message: String,
    val isSuccess: Boolean
)
