package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.data.model.*
import com.red_velvet_cake.dailytodo.data.model.login.LoginRequest
import com.red_velvet_cake.dailytodo.data.model.login.LoginResponse
import okhttp3.Response
import okio.IOException

interface TodoService {

    fun createPersonalTodo(
        personalTodoRequest: PersonalTODORequest,
        onCreatePersonalTodoSuccess: (Boolean) -> Unit,
        onCreatePersonalTodoFailure: (e: IOException) -> Unit
    )

    fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onCreateTeamTodoSuccess: (CreateTodoTeam) -> Unit,
        onCreateTeamTodoFailure: (IOException) -> Unit
    )

    fun loginUser(
        loginRequest: LoginRequest,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (exception: IOException) -> Unit
    )

    fun updatePersonalTodoStatus(
        todoId: String,
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

    fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (e: IOException) -> Unit
    )

    fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (getAllTeamTodosResponse: GetAllTeamTodosResponse) -> Unit,
        onGetAllTeamTodosFailure: (exception: IOException) -> Unit,
    )

    fun registerAccount(
        userName: String,
        password: String,
        teamId: String,
        onRegisterAccountSuccess: (registerAccountResponse: RegisterAccountResponse) -> Unit,
        onRegisterAccountFailure: (e: IOException) -> Unit
    )
}