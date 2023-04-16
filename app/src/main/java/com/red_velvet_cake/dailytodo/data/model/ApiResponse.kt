package com.red_velvet_cake.dailytodo.data.model

data class ApiResponse<T>(
    val value: T?,
    val message: String?,
    val isSuccess: Boolean
)