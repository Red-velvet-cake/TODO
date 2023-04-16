package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class GetAllTeamTodosResponse(
    @SerializedName("value") val value: List<TeamTodoResponse>,
    @SerializedName("message") val message: String,
    @SerializedName("isSuccess") val isSuccess: Boolean
)
