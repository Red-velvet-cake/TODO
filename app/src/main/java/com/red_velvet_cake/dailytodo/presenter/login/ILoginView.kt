package com.red_velvet_cake.dailytodo.presenter.login

import com.red_velvet_cake.dailytodo.model.login.LoginResponse
import okio.IOException

interface ILoginView {
    fun onLoginSuccess(loginResponse: LoginResponse)
    fun onLoginFailure(exception: IOException)
}