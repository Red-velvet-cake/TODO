package com.red_velvet_cake.dailytodo.domain

data class PersonalTodo(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val creationTime: String
)
