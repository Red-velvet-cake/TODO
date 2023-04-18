package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class TeamTodo(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("assignee") val assignee: String?,
    @SerializedName("status") var status: Int,
    @SerializedName("creationTime") val creationTime: String
)
