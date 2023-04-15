package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class CreateTodoPersonalResponseBody(
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: String,
    @SerializedName("status") val status: Int,
    @SerializedName("title") val title: String
)