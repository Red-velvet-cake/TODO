package com.red_velvet_cake.dailytodo.data.remote.api

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import okio.IOException

interface ApiService {

    fun createPersonalTodo(
        title: String,
        description: String,
        onCreatePersonalTodoSuccess: (CreateTodoPersonalResponse) -> Unit,
        onCreatePersonalTodoFailure: (e: IOException) -> Unit
    )

    fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onCreateTeamTodoSuccess: (CreateTodoTeamResponse) -> Unit,
        onCreateTeamTodoFailure: (IOException) -> Unit
    )

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusFailure: (e: IOException) -> Unit
    )

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusFailure: (e: IOException) -> Unit
    )

    fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (e: IOException) -> Unit
    )

    fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (getAllTeamTodosResponse: GetAllTeamTodosResponse) -> Unit,
        onGetAllTeamTodosFailure: (exception: IOException) -> Unit,
    )

    fun checkUserLoggedIn(
        onUserNotLoggedIn: () -> Unit
    )
}