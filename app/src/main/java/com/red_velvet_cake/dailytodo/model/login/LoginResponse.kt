package com.red_velvet_cake.dailytodo.model.login

data class LoginResponse(
    val token: String,
    val expireAt: String
)