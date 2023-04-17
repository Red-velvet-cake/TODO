package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class UpdatePersonalStatusResponse(
    @SerializedName("value") val value: String,
    @SerializedName("message") val message: String?,
    @SerializedName("isSuccess") val isSuccess: Boolean
)


