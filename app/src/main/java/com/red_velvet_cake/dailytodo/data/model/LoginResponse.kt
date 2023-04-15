package com.red_velvet_cake.dailytodo.data.model

data class LoginResponse(
    val isSuccess: Boolean,
    val message: String,
    val loginResponseBody: LoginResponseBody
)