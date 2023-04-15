package com.red_velvet_cake.dailytodo.data.model

data class CreateTodoPersonalResponse(
    val isSuccess: Boolean,
    val message: String,
    val createTodoPersonalResponseBody: CreateTodoPersonalResponseBody
)