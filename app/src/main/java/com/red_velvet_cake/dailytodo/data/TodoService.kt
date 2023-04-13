package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.data.model.login.LoginRequest
import com.red_velvet_cake.dailytodo.data.model.login.LoginResponse
import okio.IOException

interface TodoService {

    fun loginUser(
        loginRequest: LoginRequest,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (exception: IOException) -> Unit
    )

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (e: java.io.IOException) -> Unit
    )

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
        onUpdateTeamTodoStatusSuccess: (updateTeamStatusResponse: UpdateTeamTodoStatusResponse) -> Unit,
        onUpdateTeamTodoStatusFailure: (e: java.io.IOException) -> Unit
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