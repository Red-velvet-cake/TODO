package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("userId") val userId: String,
    @SerializedName("username") val username: String,
)
