package com.red_velvet_cake.dailytodo.data.model

data class CreateTodoTeamResponseBody(
     val id: String,
     val title: String,
     val description: String,
     val assignee: String,
     val status: Int,
     val creationTime: String
)