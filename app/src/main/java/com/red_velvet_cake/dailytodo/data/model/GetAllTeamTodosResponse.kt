package com.red_velvet_cake.dailytodo.data.model

data class GetAllTeamTodosResponse(
    val value: List<TeamTodo>,
    val message: String,
    val isSuccess: Boolean
)
