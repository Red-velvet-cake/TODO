package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.data.model.AllTeamTodos
import okio.IOException

interface TodoService {
    fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (allTeamTodos:AllTeamTodos) -> Unit,
        onGetAllTeamTodosFailure: (exception:IOException) -> Unit,
    )
}