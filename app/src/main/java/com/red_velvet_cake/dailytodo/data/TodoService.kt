package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.data.model.allTeamTodos
import okio.IOException

interface TodoService {
    fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (allTeamTodos) -> Unit,
        onGetAllTeamTodosFailure: (IOException) -> Unit,
    )
}