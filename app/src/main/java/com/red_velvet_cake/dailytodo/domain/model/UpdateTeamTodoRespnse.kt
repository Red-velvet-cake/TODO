package com.red_velvet_cake.dailytodo.domain.model

data class UpdateTeamTodoResponse(
    val value: String,
    val message: String?,
    val isSuccess: Boolean?
)