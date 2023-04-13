package com.red_velvet_cake.dailytodo.data.model.login

data class LoginResponse(
    val userId: String,
    val username: String,
    val message: String?,
    val isSuccess: Boolean
)