package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class PersonalTodo(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") var status: Int,
    @SerializedName("creationTime") val creationTime: String
)
