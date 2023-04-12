package com.red_velvet_cake.dailytodo.data
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.model.UpdateTeamTodoStatusResponse
import java.io.IOException
import com.red_velvet_cake.dailytodo.model.GetAllPersonalTodosResponse


interface TodoService {
    fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (e: IOException) -> Unit
    )
    fun updatePersonalTodoStatus(
        userId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (e: IOException) -> Unit
    )

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusSuccess: (updateTeamStatusResponse: UpdateTeamTodoStatusResponse) -> Unit,
        onUpdateTeamTodoStatusFailure: (e: IOException) -> Unit
    )
}