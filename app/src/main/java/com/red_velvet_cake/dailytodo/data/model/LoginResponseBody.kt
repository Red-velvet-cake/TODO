package com.red_velvet_cake.dailytodo.data.model

data class LoginResponseBody(
    val expireAt: String,
    val token: String
)