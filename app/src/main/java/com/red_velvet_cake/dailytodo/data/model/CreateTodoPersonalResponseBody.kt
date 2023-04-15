package com.red_velvet_cake.dailytodo.data.model

data class CreateTodoPersonalResponseBody(
    val creationTime: String,
    val description: String,
    val id: String,
    val status: Int,
    val title: String
)