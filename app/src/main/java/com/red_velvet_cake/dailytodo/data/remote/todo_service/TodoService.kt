package com.red_velvet_cake.dailytodo.data.remote

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse

interface TodoService {

    fun createPersonalTodo(
        title: String,
        description: String,
        onSuccess: (CreateTodoPersonalResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit
    )

    fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onSuccess: (CreateTodoTeamResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit
    )

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onFailure: (exception: Exception) -> Unit
    )

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onFailure: (exception: Exception) -> Unit
    )

    fun getAllPersonalTodos(
        onSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit
    )

    fun getAllTeamTodos(
        onSuccess: (getAllTeamTodosResponse: GetAllTeamTodosResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    )
}