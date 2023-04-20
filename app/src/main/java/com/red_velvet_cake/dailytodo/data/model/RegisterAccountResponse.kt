package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class RegisterAccountResponse(
    @SerializedName("value") val value: UserInfo,
    @SerializedName("message") val message: String,
    @SerializedName("isSuccess") val isSuccess: Boolean
)
