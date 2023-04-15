package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class CreateTodoTeamResponse(
    @SerializedName("value") val value: CreateTodoTeamResponseBody,
    @SerializedName("message") val message: String,
    @SerializedName("isSuccess") val isSuccess: Boolean
)