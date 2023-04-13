package com.red_velvet_cake.dailytodo.data.model

data class PersonalTodo(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val creationTime: String
)
