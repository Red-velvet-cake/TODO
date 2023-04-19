package com.red_velvet_cake.dailytodo.data.remote

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse

interface TodoService {

    fun createPersonalTodo(
        title: String,
        description: String,
        onCreatePersonalTodoSuccess: (CreateTodoPersonalResponse) -> Unit,
        onCreatePersonalTodoFailure: (errorMessage: String) -> Unit
    )

    fun createTeamTodo(
        title: String,
        description: String,
        assignee: String,
        onCreateTeamTodoSuccess: (CreateTodoTeamResponse) -> Unit,
        onCreateTeamTodoFailure: (errorMessage: String) -> Unit
    )

    fun loginUser(
        username: String,
        password: String,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (errorMessage: String) -> Unit
    )

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusFailure: (errorMessage: String) -> Unit
    )

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusFailure: (errorMessage: String) -> Unit
    )

    fun getAllPersonalTodos(
        onGetAllPersonalTodosSuccess: (getAllPersonalTodosResponse: GetAllPersonalTodosResponse) -> Unit,
        onGetAllPersonalTodoFailure: (errorMessage: String) -> Unit
    )

    fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (getAllTeamTodosResponse: GetAllTeamTodosResponse) -> Unit,
        onGetAllTeamTodosFailure: (errorMessage: String) -> Unit,
    )

    fun registerAccount(
        userName: String,
        password: String,
        teamId: String,
        onRegisterAccountSuccess: (registerAccountResponse: RegisterAccountResponse) -> Unit,
        onRegisterAccountFailure: (errorMessage: String) -> Unit
    )
}