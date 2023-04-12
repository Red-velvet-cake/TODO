package com.red_velvet_cake.dailytodo.domain

import com.red_velvet_cake.dailytodo.domain.model.UpdateTeamTodoResponse

interface TodoService {

    fun updateTeamTodoStatus(updateToDoStatus: (UpdateTeamTodoResponse) -> Unit)
}