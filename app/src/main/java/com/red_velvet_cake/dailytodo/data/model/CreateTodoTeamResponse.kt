package com.red_velvet_cake.dailytodo.data.model

data class CreateTodoTeamResponse(
    val value: CreateTodoTeamResponseBody,
    val message: String,
    val isSuccess: Boolean
)