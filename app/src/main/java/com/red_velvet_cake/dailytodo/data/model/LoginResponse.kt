package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("value") val loginResponseBody: LoginResponseBody
)