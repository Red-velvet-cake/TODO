package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("userID") val userId: String,
    @SerializedName("userName") val username: String,
)
