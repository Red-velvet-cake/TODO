package com.red_velvet_cake.dailytodo.data.remote.auth

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse

interface AuthenticationService {
    fun loginAccount(
        username: String,
        password: String,
        onSuccess: (response: LoginResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    )

    fun registerAccount(
        username: String,
        password: String,
        teamId: String,
        onSuccess: (response: RegisterAccountResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    )
}