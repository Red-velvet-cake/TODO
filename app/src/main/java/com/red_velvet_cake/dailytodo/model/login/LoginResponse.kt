package com.red_velvet_cake.dailytodo.model.login

data class LoginResponse(
    val userId: String,
    val username: String,
    val message: String?,
    val isSuccess: Boolean
)