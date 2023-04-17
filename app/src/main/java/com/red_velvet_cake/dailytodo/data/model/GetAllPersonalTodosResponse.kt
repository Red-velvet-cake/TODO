package com.red_velvet_cake.dailytodo.data.model

import com.google.gson.annotations.SerializedName

data class GetAllPersonalTodosResponse(
    @SerializedName("value") val value: List<PersonalTodo>,
    @SerializedName("message") val message: String,
    @SerializedName("isSuccess") val isSuccess: Boolean
)



