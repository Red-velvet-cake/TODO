package com.red_velvet_cake.dailytodo.presenter.login

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import okio.IOException

interface LoginView {
    fun onLoginSuccess(loginResponse: LoginResponse)
    fun onLoginFailure(exception: IOException)
}