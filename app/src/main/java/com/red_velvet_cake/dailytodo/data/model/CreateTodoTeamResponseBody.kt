package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class CreateTodoTeamResponseBody(
    @SerializedName("id") val id: String,
    @SerializedName("id") val title: String,
    @SerializedName("id") val description: String,
    @SerializedName("id") val assignee: String,
    @SerializedName("id") val status: Int,
    @SerializedName("id") val creationTime: String
)