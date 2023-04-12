package com.red_velvet_cake.dailytodo.data
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException
import com.red_velvet_cake.dailytodo.model.GetAllPersonalTodosResponse


interface TodoService {
    fun getAllPersonalTodos(
        onSuccess: (updatePersonalStatusResponse: GetAllPersonalTodosResponse) -> Unit,
        onFailure: (e: IOException) -> Unit
    )
    fun updatePersonalTodoStatus(
        userId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (e: IOException) -> Unit
    )
}