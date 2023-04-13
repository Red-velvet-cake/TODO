package com.red_velvet_cake.dailytodo.data.model

data class RegisterAccountResponse(
    val value: UserInfo,
    val message: String,
    val isSuccess: Boolean
)
