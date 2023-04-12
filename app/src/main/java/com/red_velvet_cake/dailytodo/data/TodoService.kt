package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException

interface TodoService {
    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (e: IOException) -> Unit
    )
}