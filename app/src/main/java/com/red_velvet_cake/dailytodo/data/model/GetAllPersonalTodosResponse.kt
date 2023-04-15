package com.red_velvet_cake.dailytodo.data.model

data class GetAllPersonalTodosResponse(
    val value: List<PersonalTodo>,
    val message: String,
    val isSuccess: Boolean
)



