package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.model.UpdateTeamTodoResponse
import java.io.IOException

interface TodoService {
    fun updatePersonalTodoStatus(
        userId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (e: IOException) -> Unit
    )

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusSuccess: (updateTeamStatusResponse: UpdateTeamTodoResponse) -> Unit,
        onUpdateTeamTodoStatusFailure: (e: IOException) -> Unit
    )
}