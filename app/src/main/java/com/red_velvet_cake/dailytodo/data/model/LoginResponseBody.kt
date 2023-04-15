package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponseBody(
    @SerializedName("expireAt") val expireAt: String,
    @SerializedName("token") val token: String
)