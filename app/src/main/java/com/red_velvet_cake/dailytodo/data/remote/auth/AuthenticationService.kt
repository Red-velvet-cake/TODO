package com.red_velvet_cake.dailytodo.data.remote.auth

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import io.reactivex.rxjava3.core.Single

interface AuthenticationService {
    fun loginAccount(
        username: String,
        password: String
    ): Single<LoginResponse>

    fun registerAccount(
        username: String,
        password: String,
        teamId: String,
        onSuccess: (response: RegisterAccountResponse) -> Unit,
        onFailure: (exception: Exception) -> Unit,
    )
}