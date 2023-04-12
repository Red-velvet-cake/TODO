package com.red_velvet_cake.dailytodo.domain

import com.red_velvet_cake.dailytodo.data.TeamTodos

interface TodoService {
    fun getTeamToDo(presentTeamTodo:(TeamTodos)->Unit)
}