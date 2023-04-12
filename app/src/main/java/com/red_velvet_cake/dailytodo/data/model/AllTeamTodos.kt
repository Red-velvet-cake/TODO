package com.red_velvet_cake.dailytodo.data.model

data class AllTeamTodos(
    val value:List<TeamTodo>,
    val message:String?,
    val isSuccess:Boolean
)
