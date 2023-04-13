package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.model.login.LoginRequest
import com.red_velvet_cake.dailytodo.model.login.LoginResponse
import okio.IOException

interface TodoService {
    fun loginUser(
        loginRequest: LoginRequest,
        onLoginUserSuccess: (loginResponse: LoginResponse) -> Unit,
        onLoginUserFailure: (exception: IOException) -> Unit
    )
}