package com.red_velvet_cake.dailytodo.model.login

data class ApiResponse<T>(
    val value: T?,
    val message: String?,
    val isSuccess: Boolean
)